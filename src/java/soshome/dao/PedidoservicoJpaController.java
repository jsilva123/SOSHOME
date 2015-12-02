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
import soshome.model.bean.Cliente;
import soshome.model.bean.Profissional;
import soshome.model.bean.Tiposervico;
import soshome.model.bean.Servicoprestado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import soshome.dao.exceptions.IllegalOrphanException;
import soshome.dao.exceptions.NonexistentEntityException;
import soshome.model.bean.Pedidoservico;

/**
 *
 * @author Jorge
 */
public class PedidoservicoJpaController implements Serializable {

    public PedidoservicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedidoservico pedidoservico) {
        if (pedidoservico.getServicoprestadoList() == null) {
            pedidoservico.setServicoprestadoList(new ArrayList<Servicoprestado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente idcliente = pedidoservico.getIdcliente();
            if (idcliente != null) {
                idcliente = em.getReference(idcliente.getClass(), idcliente.getIdcliente());
                pedidoservico.setIdcliente(idcliente);
            }
            Profissional idprofissional = pedidoservico.getIdprofissional();
            if (idprofissional != null) {
                idprofissional = em.getReference(idprofissional.getClass(), idprofissional.getIdprofissional());
                pedidoservico.setIdprofissional(idprofissional);
            }
            Tiposervico idtiposervico = pedidoservico.getIdtiposervico();
            if (idtiposervico != null) {
                idtiposervico = em.getReference(idtiposervico.getClass(), idtiposervico.getIdtiposervico());
                pedidoservico.setIdtiposervico(idtiposervico);
            }
            List<Servicoprestado> attachedServicoprestadoList = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListServicoprestadoToAttach : pedidoservico.getServicoprestadoList()) {
                servicoprestadoListServicoprestadoToAttach = em.getReference(servicoprestadoListServicoprestadoToAttach.getClass(), servicoprestadoListServicoprestadoToAttach.getServicoprestadoPK());
                attachedServicoprestadoList.add(servicoprestadoListServicoprestadoToAttach);
            }
            pedidoservico.setServicoprestadoList(attachedServicoprestadoList);
            em.persist(pedidoservico);
            if (idcliente != null) {
                idcliente.getPedidoservicoList().add(pedidoservico);
                idcliente = em.merge(idcliente);
            }
            if (idprofissional != null) {
                idprofissional.getPedidoservicoList().add(pedidoservico);
                idprofissional = em.merge(idprofissional);
            }
            if (idtiposervico != null) {
                idtiposervico.getPedidoservicoList().add(pedidoservico);
                idtiposervico = em.merge(idtiposervico);
            }
            for (Servicoprestado servicoprestadoListServicoprestado : pedidoservico.getServicoprestadoList()) {
                Pedidoservico oldPedidoservicoOfServicoprestadoListServicoprestado = servicoprestadoListServicoprestado.getPedidoservico();
                servicoprestadoListServicoprestado.setPedidoservico(pedidoservico);
                servicoprestadoListServicoprestado = em.merge(servicoprestadoListServicoprestado);
                if (oldPedidoservicoOfServicoprestadoListServicoprestado != null) {
                    oldPedidoservicoOfServicoprestadoListServicoprestado.getServicoprestadoList().remove(servicoprestadoListServicoprestado);
                    oldPedidoservicoOfServicoprestadoListServicoprestado = em.merge(oldPedidoservicoOfServicoprestadoListServicoprestado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedidoservico pedidoservico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedidoservico persistentPedidoservico = em.find(Pedidoservico.class, pedidoservico.getIdpedido());
            Cliente idclienteOld = persistentPedidoservico.getIdcliente();
            Cliente idclienteNew = pedidoservico.getIdcliente();
            Profissional idprofissionalOld = persistentPedidoservico.getIdprofissional();
            Profissional idprofissionalNew = pedidoservico.getIdprofissional();
            Tiposervico idtiposervicoOld = persistentPedidoservico.getIdtiposervico();
            Tiposervico idtiposervicoNew = pedidoservico.getIdtiposervico();
            List<Servicoprestado> servicoprestadoListOld = persistentPedidoservico.getServicoprestadoList();
            List<Servicoprestado> servicoprestadoListNew = pedidoservico.getServicoprestadoList();
            List<String> illegalOrphanMessages = null;
            for (Servicoprestado servicoprestadoListOldServicoprestado : servicoprestadoListOld) {
                if (!servicoprestadoListNew.contains(servicoprestadoListOldServicoprestado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Servicoprestado " + servicoprestadoListOldServicoprestado + " since its pedidoservico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idclienteNew != null) {
                idclienteNew = em.getReference(idclienteNew.getClass(), idclienteNew.getIdcliente());
                pedidoservico.setIdcliente(idclienteNew);
            }
            if (idprofissionalNew != null) {
                idprofissionalNew = em.getReference(idprofissionalNew.getClass(), idprofissionalNew.getIdprofissional());
                pedidoservico.setIdprofissional(idprofissionalNew);
            }
            if (idtiposervicoNew != null) {
                idtiposervicoNew = em.getReference(idtiposervicoNew.getClass(), idtiposervicoNew.getIdtiposervico());
                pedidoservico.setIdtiposervico(idtiposervicoNew);
            }
            List<Servicoprestado> attachedServicoprestadoListNew = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListNewServicoprestadoToAttach : servicoprestadoListNew) {
                servicoprestadoListNewServicoprestadoToAttach = em.getReference(servicoprestadoListNewServicoprestadoToAttach.getClass(), servicoprestadoListNewServicoprestadoToAttach.getServicoprestadoPK());
                attachedServicoprestadoListNew.add(servicoprestadoListNewServicoprestadoToAttach);
            }
            servicoprestadoListNew = attachedServicoprestadoListNew;
            pedidoservico.setServicoprestadoList(servicoprestadoListNew);
            pedidoservico = em.merge(pedidoservico);
            if (idclienteOld != null && !idclienteOld.equals(idclienteNew)) {
                idclienteOld.getPedidoservicoList().remove(pedidoservico);
                idclienteOld = em.merge(idclienteOld);
            }
            if (idclienteNew != null && !idclienteNew.equals(idclienteOld)) {
                idclienteNew.getPedidoservicoList().add(pedidoservico);
                idclienteNew = em.merge(idclienteNew);
            }
            if (idprofissionalOld != null && !idprofissionalOld.equals(idprofissionalNew)) {
                idprofissionalOld.getPedidoservicoList().remove(pedidoservico);
                idprofissionalOld = em.merge(idprofissionalOld);
            }
            if (idprofissionalNew != null && !idprofissionalNew.equals(idprofissionalOld)) {
                idprofissionalNew.getPedidoservicoList().add(pedidoservico);
                idprofissionalNew = em.merge(idprofissionalNew);
            }
            if (idtiposervicoOld != null && !idtiposervicoOld.equals(idtiposervicoNew)) {
                idtiposervicoOld.getPedidoservicoList().remove(pedidoservico);
                idtiposervicoOld = em.merge(idtiposervicoOld);
            }
            if (idtiposervicoNew != null && !idtiposervicoNew.equals(idtiposervicoOld)) {
                idtiposervicoNew.getPedidoservicoList().add(pedidoservico);
                idtiposervicoNew = em.merge(idtiposervicoNew);
            }
            for (Servicoprestado servicoprestadoListNewServicoprestado : servicoprestadoListNew) {
                if (!servicoprestadoListOld.contains(servicoprestadoListNewServicoprestado)) {
                    Pedidoservico oldPedidoservicoOfServicoprestadoListNewServicoprestado = servicoprestadoListNewServicoprestado.getPedidoservico();
                    servicoprestadoListNewServicoprestado.setPedidoservico(pedidoservico);
                    servicoprestadoListNewServicoprestado = em.merge(servicoprestadoListNewServicoprestado);
                    if (oldPedidoservicoOfServicoprestadoListNewServicoprestado != null && !oldPedidoservicoOfServicoprestadoListNewServicoprestado.equals(pedidoservico)) {
                        oldPedidoservicoOfServicoprestadoListNewServicoprestado.getServicoprestadoList().remove(servicoprestadoListNewServicoprestado);
                        oldPedidoservicoOfServicoprestadoListNewServicoprestado = em.merge(oldPedidoservicoOfServicoprestadoListNewServicoprestado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedidoservico.getIdpedido();
                if (findPedidoservico(id) == null) {
                    throw new NonexistentEntityException("The pedidoservico with id " + id + " no longer exists.");
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
            Pedidoservico pedidoservico;
            try {
                pedidoservico = em.getReference(Pedidoservico.class, id);
                pedidoservico.getIdpedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidoservico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Servicoprestado> servicoprestadoListOrphanCheck = pedidoservico.getServicoprestadoList();
            for (Servicoprestado servicoprestadoListOrphanCheckServicoprestado : servicoprestadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedidoservico (" + pedidoservico + ") cannot be destroyed since the Servicoprestado " + servicoprestadoListOrphanCheckServicoprestado + " in its servicoprestadoList field has a non-nullable pedidoservico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente idcliente = pedidoservico.getIdcliente();
            if (idcliente != null) {
                idcliente.getPedidoservicoList().remove(pedidoservico);
                idcliente = em.merge(idcliente);
            }
            Profissional idprofissional = pedidoservico.getIdprofissional();
            if (idprofissional != null) {
                idprofissional.getPedidoservicoList().remove(pedidoservico);
                idprofissional = em.merge(idprofissional);
            }
            Tiposervico idtiposervico = pedidoservico.getIdtiposervico();
            if (idtiposervico != null) {
                idtiposervico.getPedidoservicoList().remove(pedidoservico);
                idtiposervico = em.merge(idtiposervico);
            }
            em.remove(pedidoservico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedidoservico> findPedidoservicoEntities() {
        return findPedidoservicoEntities(true, -1, -1);
    }

    public List<Pedidoservico> findPedidoservicoEntities(int maxResults, int firstResult) {
        return findPedidoservicoEntities(false, maxResults, firstResult);
    }

    public List<Pedidoservico> findByStatusCliente(String status, Cliente idcliente) {

        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Pedidoservico.findByStatusCliente");
        query.setParameter("status", status);
        query.setParameter("idcliente", idcliente);
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
        }

        return null;
    }

    public List<Pedidoservico> findCriados() {

        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Pedidoservico.findByStatus");
        query.setParameter("status", "CRIADO");
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
        }

        return null;
    }
    
   public long findCountTiposervico(Tiposervico idTiposervico) {

        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Pedidoservico.findCountTipoServico");
        query.setParameter("idtiposervico", idTiposervico);
        try {
            return (long)query.getSingleResult();
        } catch (NoResultException nre) {
        }

        return 0;
    }
    public List<Pedidoservico> findByStatusProfissional(String status, Profissional idprofissional) {

        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Pedidoservico.findByStatusProfissional");
        query.setParameter("status", status);
        query.setParameter("idprofissional", idprofissional);
        try {
            return query.getResultList();
        } catch (NoResultException nre) {
        }

        return null;
    }

    private List<Pedidoservico> findPedidoservicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedidoservico.class));
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

    public Pedidoservico findPedidoservico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedidoservico.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoservicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedidoservico> rt = cq.from(Pedidoservico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
