/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soshome.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import soshome.dao.exceptions.NonexistentEntityException;
import soshome.dao.exceptions.PreexistingEntityException;
import soshome.model.bean.Pedidoservico;
import soshome.model.bean.Profissional;
import soshome.model.bean.Servicoprestado;
import soshome.model.bean.ServicoprestadoPK;
import soshome.model.bean.Tiposervico;

/**
 *
 * @author Jorge
 */
public class ServicoprestadoJpaController implements Serializable {

    public ServicoprestadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicoprestado servicoprestado) throws PreexistingEntityException, Exception {
        if (servicoprestado.getServicoprestadoPK() == null) {
            servicoprestado.setServicoprestadoPK(new ServicoprestadoPK());
        }
        servicoprestado.getServicoprestadoPK().setIdprofissional(servicoprestado.getProfissional().getIdprofissional());
        servicoprestado.getServicoprestadoPK().setIdpedidoservico(servicoprestado.getPedidoservico().getIdpedido());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedidoservico pedidoservico = servicoprestado.getPedidoservico();
            if (pedidoservico != null) {
                pedidoservico = em.getReference(pedidoservico.getClass(), pedidoservico.getIdpedido());
                servicoprestado.setPedidoservico(pedidoservico);
            }
            Profissional profissional = servicoprestado.getProfissional();
            if (profissional != null) {
                profissional = em.getReference(profissional.getClass(), profissional.getIdprofissional());
                servicoprestado.setProfissional(profissional);
            }
            Tiposervico idtiposervico = servicoprestado.getIdtiposervico();
            if (idtiposervico != null) {
                idtiposervico = em.getReference(idtiposervico.getClass(), idtiposervico.getIdtiposervico());
                servicoprestado.setIdtiposervico(idtiposervico);
            }
            em.persist(servicoprestado);
            if (pedidoservico != null) {
                pedidoservico.getServicoprestadoList().add(servicoprestado);
                pedidoservico = em.merge(pedidoservico);
            }
            if (profissional != null) {
                profissional.getServicoprestadoList().add(servicoprestado);
                profissional = em.merge(profissional);
            }
            if (idtiposervico != null) {
                idtiposervico.getServicoprestadoList().add(servicoprestado);
                idtiposervico = em.merge(idtiposervico);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicoprestado(servicoprestado.getServicoprestadoPK()) != null) {
                throw new PreexistingEntityException("Servicoprestado " + servicoprestado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicoprestado servicoprestado) throws NonexistentEntityException, Exception {
        servicoprestado.getServicoprestadoPK().setIdprofissional(servicoprestado.getProfissional().getIdprofissional());
        servicoprestado.getServicoprestadoPK().setIdpedidoservico(servicoprestado.getPedidoservico().getIdpedido());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicoprestado persistentServicoprestado = em.find(Servicoprestado.class, servicoprestado.getServicoprestadoPK());
            Pedidoservico pedidoservicoOld = persistentServicoprestado.getPedidoservico();
            Pedidoservico pedidoservicoNew = servicoprestado.getPedidoservico();
            Profissional profissionalOld = persistentServicoprestado.getProfissional();
            Profissional profissionalNew = servicoprestado.getProfissional();
            Tiposervico idtiposervicoOld = persistentServicoprestado.getIdtiposervico();
            Tiposervico idtiposervicoNew = servicoprestado.getIdtiposervico();
            if (pedidoservicoNew != null) {
                pedidoservicoNew = em.getReference(pedidoservicoNew.getClass(), pedidoservicoNew.getIdpedido());
                servicoprestado.setPedidoservico(pedidoservicoNew);
            }
            if (profissionalNew != null) {
                profissionalNew = em.getReference(profissionalNew.getClass(), profissionalNew.getIdprofissional());
                servicoprestado.setProfissional(profissionalNew);
            }
            if (idtiposervicoNew != null) {
                idtiposervicoNew = em.getReference(idtiposervicoNew.getClass(), idtiposervicoNew.getIdtiposervico());
                servicoprestado.setIdtiposervico(idtiposervicoNew);
            }
            servicoprestado = em.merge(servicoprestado);
            if (pedidoservicoOld != null && !pedidoservicoOld.equals(pedidoservicoNew)) {
                pedidoservicoOld.getServicoprestadoList().remove(servicoprestado);
                pedidoservicoOld = em.merge(pedidoservicoOld);
            }
            if (pedidoservicoNew != null && !pedidoservicoNew.equals(pedidoservicoOld)) {
                pedidoservicoNew.getServicoprestadoList().add(servicoprestado);
                pedidoservicoNew = em.merge(pedidoservicoNew);
            }
            if (profissionalOld != null && !profissionalOld.equals(profissionalNew)) {
                profissionalOld.getServicoprestadoList().remove(servicoprestado);
                profissionalOld = em.merge(profissionalOld);
            }
            if (profissionalNew != null && !profissionalNew.equals(profissionalOld)) {
                profissionalNew.getServicoprestadoList().add(servicoprestado);
                profissionalNew = em.merge(profissionalNew);
            }
            if (idtiposervicoOld != null && !idtiposervicoOld.equals(idtiposervicoNew)) {
                idtiposervicoOld.getServicoprestadoList().remove(servicoprestado);
                idtiposervicoOld = em.merge(idtiposervicoOld);
            }
            if (idtiposervicoNew != null && !idtiposervicoNew.equals(idtiposervicoOld)) {
                idtiposervicoNew.getServicoprestadoList().add(servicoprestado);
                idtiposervicoNew = em.merge(idtiposervicoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ServicoprestadoPK id = servicoprestado.getServicoprestadoPK();
                if (findServicoprestado(id) == null) {
                    throw new NonexistentEntityException("The servicoprestado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ServicoprestadoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicoprestado servicoprestado;
            try {
                servicoprestado = em.getReference(Servicoprestado.class, id);
                servicoprestado.getServicoprestadoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicoprestado with id " + id + " no longer exists.", enfe);
            }
            Pedidoservico pedidoservico = servicoprestado.getPedidoservico();
            if (pedidoservico != null) {
                pedidoservico.getServicoprestadoList().remove(servicoprestado);
                pedidoservico = em.merge(pedidoservico);
            }
            Profissional profissional = servicoprestado.getProfissional();
            if (profissional != null) {
                profissional.getServicoprestadoList().remove(servicoprestado);
                profissional = em.merge(profissional);
            }
            Tiposervico idtiposervico = servicoprestado.getIdtiposervico();
            if (idtiposervico != null) {
                idtiposervico.getServicoprestadoList().remove(servicoprestado);
                idtiposervico = em.merge(idtiposervico);
            }
            em.remove(servicoprestado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicoprestado> findServicoprestadoEntities() {
        return findServicoprestadoEntities(true, -1, -1);
    }

    public List<Servicoprestado> findServicoprestadoEntities(int maxResults, int firstResult) {
        return findServicoprestadoEntities(false, maxResults, firstResult);
    }

    private List<Servicoprestado> findServicoprestadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicoprestado.class));
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

    public Servicoprestado findServicoprestado(ServicoprestadoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicoprestado.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicoprestadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicoprestado> rt = cq.from(Servicoprestado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
