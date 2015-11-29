/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import soshome.model.bean.Pedidoservico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import soshome.dao.exceptions.IllegalOrphanException;
import soshome.dao.exceptions.NonexistentEntityException;
import soshome.model.bean.Cliente;

/**
 *
 * @author Jorge
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Cliente findClienteByLogin(String nome) {
        Cliente cliente = null;
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Cliente.findByNome");
        query.setParameter("nome", nome);
        try {
            cliente = (Cliente) query.getSingleResult();
        } catch (NoResultException nre) {
        }

        return cliente;
    }

    public void create(Cliente cliente) {
        if (cliente.getPedidoservicoList() == null) {
            cliente.setPedidoservicoList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedidoservico> attachedPedidoservicoList = new ArrayList<>();
            for (Pedidoservico pedidoservicoListPedidoservicoToAttach : cliente.getPedidoservicoList()) {
                pedidoservicoListPedidoservicoToAttach = em.getReference(pedidoservicoListPedidoservicoToAttach.getClass(), pedidoservicoListPedidoservicoToAttach.getIdpedido());
                attachedPedidoservicoList.add(pedidoservicoListPedidoservicoToAttach);
            }
            cliente.setPedidoservicoList(attachedPedidoservicoList);
            em.persist(cliente);
            for (Pedidoservico pedidoservicoListPedidoservico : cliente.getPedidoservicoList()) {
                Cliente oldIdclienteOfPedidoservicoListPedidoservico = pedidoservicoListPedidoservico.getIdcliente();
                pedidoservicoListPedidoservico.setIdcliente(cliente);
                pedidoservicoListPedidoservico = em.merge(pedidoservicoListPedidoservico);
                if (oldIdclienteOfPedidoservicoListPedidoservico != null) {
                    oldIdclienteOfPedidoservicoListPedidoservico.getPedidoservicoList().remove(pedidoservicoListPedidoservico);
                    oldIdclienteOfPedidoservicoListPedidoservico = em.merge(oldIdclienteOfPedidoservicoListPedidoservico);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdcliente());
            List<Pedidoservico> pedidoservicoListOld = persistentCliente.getPedidoservicoList();
            List<Pedidoservico> pedidoservicoListNew = cliente.getPedidoservicoList();
            List<String> illegalOrphanMessages = null;
            for (Pedidoservico pedidoservicoListOldPedidoservico : pedidoservicoListOld) {
                if (!pedidoservicoListNew.contains(pedidoservicoListOldPedidoservico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedidoservico " + pedidoservicoListOldPedidoservico + " since its idcliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pedidoservico> attachedPedidoservicoListNew = new ArrayList<Pedidoservico>();
            for (Pedidoservico pedidoservicoListNewPedidoservicoToAttach : pedidoservicoListNew) {
                pedidoservicoListNewPedidoservicoToAttach = em.getReference(pedidoservicoListNewPedidoservicoToAttach.getClass(), pedidoservicoListNewPedidoservicoToAttach.getIdpedido());
                attachedPedidoservicoListNew.add(pedidoservicoListNewPedidoservicoToAttach);
            }
            pedidoservicoListNew = attachedPedidoservicoListNew;
            cliente.setPedidoservicoList(pedidoservicoListNew);
            cliente = em.merge(cliente);
            for (Pedidoservico pedidoservicoListNewPedidoservico : pedidoservicoListNew) {
                if (!pedidoservicoListOld.contains(pedidoservicoListNewPedidoservico)) {
                    Cliente oldIdclienteOfPedidoservicoListNewPedidoservico = pedidoservicoListNewPedidoservico.getIdcliente();
                    pedidoservicoListNewPedidoservico.setIdcliente(cliente);
                    pedidoservicoListNewPedidoservico = em.merge(pedidoservicoListNewPedidoservico);
                    if (oldIdclienteOfPedidoservicoListNewPedidoservico != null && !oldIdclienteOfPedidoservicoListNewPedidoservico.equals(cliente)) {
                        oldIdclienteOfPedidoservicoListNewPedidoservico.getPedidoservicoList().remove(pedidoservicoListNewPedidoservico);
                        oldIdclienteOfPedidoservicoListNewPedidoservico = em.merge(oldIdclienteOfPedidoservicoListNewPedidoservico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getIdcliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pedidoservico> pedidoservicoListOrphanCheck = cliente.getPedidoservicoList();
            for (Pedidoservico pedidoservicoListOrphanCheckPedidoservico : pedidoservicoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Pedidoservico " + pedidoservicoListOrphanCheckPedidoservico + " in its pedidoservicoList field has a non-nullable idcliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
