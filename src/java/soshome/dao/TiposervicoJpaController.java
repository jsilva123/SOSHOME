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
import soshome.dao.exceptions.IllegalOrphanException;
import soshome.dao.exceptions.NonexistentEntityException;
import soshome.model.bean.Servicoprestado;
import soshome.model.bean.Tiposervico;

/**
 *
 * @author Jorge
 */
public class TiposervicoJpaController implements Serializable {

    public TiposervicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tiposervico tiposervico) {
        if (tiposervico.getPedidoservicoList() == null) {
            tiposervico.setPedidoservicoList(new ArrayList<Pedidoservico>());
        }
        if (tiposervico.getServicoprestadoList() == null) {
            tiposervico.setServicoprestadoList(new ArrayList<Servicoprestado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedidoservico> attachedPedidoservicoList = new ArrayList<Pedidoservico>();
            for (Pedidoservico pedidoservicoListPedidoservicoToAttach : tiposervico.getPedidoservicoList()) {
                pedidoservicoListPedidoservicoToAttach = em.getReference(pedidoservicoListPedidoservicoToAttach.getClass(), pedidoservicoListPedidoservicoToAttach.getIdpedido());
                attachedPedidoservicoList.add(pedidoservicoListPedidoservicoToAttach);
            }
            tiposervico.setPedidoservicoList(attachedPedidoservicoList);
            List<Servicoprestado> attachedServicoprestadoList = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListServicoprestadoToAttach : tiposervico.getServicoprestadoList()) {
                servicoprestadoListServicoprestadoToAttach = em.getReference(servicoprestadoListServicoprestadoToAttach.getClass(), servicoprestadoListServicoprestadoToAttach.getServicoprestadoPK());
                attachedServicoprestadoList.add(servicoprestadoListServicoprestadoToAttach);
            }
            tiposervico.setServicoprestadoList(attachedServicoprestadoList);
            em.persist(tiposervico);
            for (Pedidoservico pedidoservicoListPedidoservico : tiposervico.getPedidoservicoList()) {
                Tiposervico oldIdtiposervicoOfPedidoservicoListPedidoservico = pedidoservicoListPedidoservico.getIdtiposervico();
                pedidoservicoListPedidoservico.setIdtiposervico(tiposervico);
                pedidoservicoListPedidoservico = em.merge(pedidoservicoListPedidoservico);
                if (oldIdtiposervicoOfPedidoservicoListPedidoservico != null) {
                    oldIdtiposervicoOfPedidoservicoListPedidoservico.getPedidoservicoList().remove(pedidoservicoListPedidoservico);
                    oldIdtiposervicoOfPedidoservicoListPedidoservico = em.merge(oldIdtiposervicoOfPedidoservicoListPedidoservico);
                }
            }
            for (Servicoprestado servicoprestadoListServicoprestado : tiposervico.getServicoprestadoList()) {
                Tiposervico oldIdtiposervicoOfServicoprestadoListServicoprestado = servicoprestadoListServicoprestado.getIdtiposervico();
                servicoprestadoListServicoprestado.setIdtiposervico(tiposervico);
                servicoprestadoListServicoprestado = em.merge(servicoprestadoListServicoprestado);
                if (oldIdtiposervicoOfServicoprestadoListServicoprestado != null) {
                    oldIdtiposervicoOfServicoprestadoListServicoprestado.getServicoprestadoList().remove(servicoprestadoListServicoprestado);
                    oldIdtiposervicoOfServicoprestadoListServicoprestado = em.merge(oldIdtiposervicoOfServicoprestadoListServicoprestado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tiposervico tiposervico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tiposervico persistentTiposervico = em.find(Tiposervico.class, tiposervico.getIdtiposervico());
            List<Pedidoservico> pedidoservicoListOld = persistentTiposervico.getPedidoservicoList();
            List<Pedidoservico> pedidoservicoListNew = tiposervico.getPedidoservicoList();
            List<Servicoprestado> servicoprestadoListOld = persistentTiposervico.getServicoprestadoList();
            List<Servicoprestado> servicoprestadoListNew = tiposervico.getServicoprestadoList();
            List<String> illegalOrphanMessages = null;
            for (Pedidoservico pedidoservicoListOldPedidoservico : pedidoservicoListOld) {
                if (!pedidoservicoListNew.contains(pedidoservicoListOldPedidoservico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedidoservico " + pedidoservicoListOldPedidoservico + " since its idtiposervico field is not nullable.");
                }
            }
            for (Servicoprestado servicoprestadoListOldServicoprestado : servicoprestadoListOld) {
                if (!servicoprestadoListNew.contains(servicoprestadoListOldServicoprestado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Servicoprestado " + servicoprestadoListOldServicoprestado + " since its idtiposervico field is not nullable.");
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
            tiposervico.setPedidoservicoList(pedidoservicoListNew);
            List<Servicoprestado> attachedServicoprestadoListNew = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListNewServicoprestadoToAttach : servicoprestadoListNew) {
                servicoprestadoListNewServicoprestadoToAttach = em.getReference(servicoprestadoListNewServicoprestadoToAttach.getClass(), servicoprestadoListNewServicoprestadoToAttach.getServicoprestadoPK());
                attachedServicoprestadoListNew.add(servicoprestadoListNewServicoprestadoToAttach);
            }
            servicoprestadoListNew = attachedServicoprestadoListNew;
            tiposervico.setServicoprestadoList(servicoprestadoListNew);
            tiposervico = em.merge(tiposervico);
            for (Pedidoservico pedidoservicoListNewPedidoservico : pedidoservicoListNew) {
                if (!pedidoservicoListOld.contains(pedidoservicoListNewPedidoservico)) {
                    Tiposervico oldIdtiposervicoOfPedidoservicoListNewPedidoservico = pedidoservicoListNewPedidoservico.getIdtiposervico();
                    pedidoservicoListNewPedidoservico.setIdtiposervico(tiposervico);
                    pedidoservicoListNewPedidoservico = em.merge(pedidoservicoListNewPedidoservico);
                    if (oldIdtiposervicoOfPedidoservicoListNewPedidoservico != null && !oldIdtiposervicoOfPedidoservicoListNewPedidoservico.equals(tiposervico)) {
                        oldIdtiposervicoOfPedidoservicoListNewPedidoservico.getPedidoservicoList().remove(pedidoservicoListNewPedidoservico);
                        oldIdtiposervicoOfPedidoservicoListNewPedidoservico = em.merge(oldIdtiposervicoOfPedidoservicoListNewPedidoservico);
                    }
                }
            }
            for (Servicoprestado servicoprestadoListNewServicoprestado : servicoprestadoListNew) {
                if (!servicoprestadoListOld.contains(servicoprestadoListNewServicoprestado)) {
                    Tiposervico oldIdtiposervicoOfServicoprestadoListNewServicoprestado = servicoprestadoListNewServicoprestado.getIdtiposervico();
                    servicoprestadoListNewServicoprestado.setIdtiposervico(tiposervico);
                    servicoprestadoListNewServicoprestado = em.merge(servicoprestadoListNewServicoprestado);
                    if (oldIdtiposervicoOfServicoprestadoListNewServicoprestado != null && !oldIdtiposervicoOfServicoprestadoListNewServicoprestado.equals(tiposervico)) {
                        oldIdtiposervicoOfServicoprestadoListNewServicoprestado.getServicoprestadoList().remove(servicoprestadoListNewServicoprestado);
                        oldIdtiposervicoOfServicoprestadoListNewServicoprestado = em.merge(oldIdtiposervicoOfServicoprestadoListNewServicoprestado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tiposervico.getIdtiposervico();
                if (findTiposervico(id) == null) {
                    throw new NonexistentEntityException("The tiposervico with id " + id + " no longer exists.");
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
            Tiposervico tiposervico;
            try {
                tiposervico = em.getReference(Tiposervico.class, id);
                tiposervico.getIdtiposervico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tiposervico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pedidoservico> pedidoservicoListOrphanCheck = tiposervico.getPedidoservicoList();
            for (Pedidoservico pedidoservicoListOrphanCheckPedidoservico : pedidoservicoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tiposervico (" + tiposervico + ") cannot be destroyed since the Pedidoservico " + pedidoservicoListOrphanCheckPedidoservico + " in its pedidoservicoList field has a non-nullable idtiposervico field.");
            }
            List<Servicoprestado> servicoprestadoListOrphanCheck = tiposervico.getServicoprestadoList();
            for (Servicoprestado servicoprestadoListOrphanCheckServicoprestado : servicoprestadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tiposervico (" + tiposervico + ") cannot be destroyed since the Servicoprestado " + servicoprestadoListOrphanCheckServicoprestado + " in its servicoprestadoList field has a non-nullable idtiposervico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tiposervico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tiposervico> findTiposervicoEntities() {
        return findTiposervicoEntities(true, -1, -1);
    }

    public List<Tiposervico> findTiposervicoEntities(int maxResults, int firstResult) {
        return findTiposervicoEntities(false, maxResults, firstResult);
    }

    private List<Tiposervico> findTiposervicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tiposervico.class));
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

    public Tiposervico findTiposervico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tiposervico.class, id);
        } finally {
            em.close();
        }
    }

    public int getTiposervicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tiposervico> rt = cq.from(Tiposervico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
