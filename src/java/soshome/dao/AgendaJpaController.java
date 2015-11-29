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
import soshome.model.bean.Agenda;
import soshome.model.bean.AgendaPK;
import soshome.model.bean.Profissional;

/**
 *
 * @author Jorge
 */
public class AgendaJpaController implements Serializable {

    public AgendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Agenda agenda) throws PreexistingEntityException, Exception {
        if (agenda.getAgendaPK() == null) {
            agenda.setAgendaPK(new AgendaPK());
        }
        agenda.getAgendaPK().setIdprofissional(agenda.getProfissional().getIdprofissional());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profissional profissional = agenda.getProfissional();
            if (profissional != null) {
                profissional = em.getReference(profissional.getClass(), profissional.getIdprofissional());
                agenda.setProfissional(profissional);
            }
            em.persist(agenda);
            if (profissional != null) {
                profissional.getAgendaList().add(agenda);
                profissional = em.merge(profissional);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAgenda(agenda.getAgendaPK()) != null) {
                throw new PreexistingEntityException("Agenda " + agenda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Agenda agenda) throws NonexistentEntityException, Exception {
        agenda.getAgendaPK().setIdprofissional(agenda.getProfissional().getIdprofissional());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agenda persistentAgenda = em.find(Agenda.class, agenda.getAgendaPK());
            Profissional profissionalOld = persistentAgenda.getProfissional();
            Profissional profissionalNew = agenda.getProfissional();
            if (profissionalNew != null) {
                profissionalNew = em.getReference(profissionalNew.getClass(), profissionalNew.getIdprofissional());
                agenda.setProfissional(profissionalNew);
            }
            agenda = em.merge(agenda);
            if (profissionalOld != null && !profissionalOld.equals(profissionalNew)) {
                profissionalOld.getAgendaList().remove(agenda);
                profissionalOld = em.merge(profissionalOld);
            }
            if (profissionalNew != null && !profissionalNew.equals(profissionalOld)) {
                profissionalNew.getAgendaList().add(agenda);
                profissionalNew = em.merge(profissionalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AgendaPK id = agenda.getAgendaPK();
                if (findAgenda(id) == null) {
                    throw new NonexistentEntityException("The agenda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AgendaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Agenda agenda;
            try {
                agenda = em.getReference(Agenda.class, id);
                agenda.getAgendaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The agenda with id " + id + " no longer exists.", enfe);
            }
            Profissional profissional = agenda.getProfissional();
            if (profissional != null) {
                profissional.getAgendaList().remove(agenda);
                profissional = em.merge(profissional);
            }
            em.remove(agenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Agenda> findAgendaEntities() {
        return findAgendaEntities(true, -1, -1);
    }

    public List<Agenda> findAgendaEntities(int maxResults, int firstResult) {
        return findAgendaEntities(false, maxResults, firstResult);
    }

    private List<Agenda> findAgendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Agenda.class));
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

    public Agenda findAgenda(AgendaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Agenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getAgendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Agenda> rt = cq.from(Agenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
