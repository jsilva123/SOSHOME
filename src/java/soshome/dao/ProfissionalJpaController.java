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
import soshome.model.bean.Agenda;
import soshome.model.bean.Profissional;
import soshome.model.bean.Servicoprestado;

/**
 *
 * @author Jorge
 */
public class ProfissionalJpaController implements Serializable {

    public ProfissionalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Profissional findProfissionalByLogin(String nome) {
        Profissional profissional = null;
        EntityManager em = getEntityManager();
        Query query = em.createNamedQuery("Profissional.findByNome");
        query.setParameter("nome", nome);
        try {
            profissional = (Profissional) query.getSingleResult();
        } catch (NoResultException nre) {
        }

        return profissional;
    }

    public void create(Profissional profissional) {
        if (profissional.getPedidoservicoList() == null) {
            profissional.setPedidoservicoList(new ArrayList<Pedidoservico>());
        }
        if (profissional.getAgendaList() == null) {
            profissional.setAgendaList(new ArrayList<Agenda>());
        }
        if (profissional.getServicoprestadoList() == null) {
            profissional.setServicoprestadoList(new ArrayList<Servicoprestado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedidoservico> attachedPedidoservicoList = new ArrayList<Pedidoservico>();
            for (Pedidoservico pedidoservicoListPedidoservicoToAttach : profissional.getPedidoservicoList()) {
                pedidoservicoListPedidoservicoToAttach = em.getReference(pedidoservicoListPedidoservicoToAttach.getClass(), pedidoservicoListPedidoservicoToAttach.getIdpedido());
                attachedPedidoservicoList.add(pedidoservicoListPedidoservicoToAttach);
            }
            profissional.setPedidoservicoList(attachedPedidoservicoList);
            List<Agenda> attachedAgendaList = new ArrayList<Agenda>();
            for (Agenda agendaListAgendaToAttach : profissional.getAgendaList()) {
                agendaListAgendaToAttach = em.getReference(agendaListAgendaToAttach.getClass(), agendaListAgendaToAttach.getAgendaPK());
                attachedAgendaList.add(agendaListAgendaToAttach);
            }
            profissional.setAgendaList(attachedAgendaList);
            List<Servicoprestado> attachedServicoprestadoList = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListServicoprestadoToAttach : profissional.getServicoprestadoList()) {
                servicoprestadoListServicoprestadoToAttach = em.getReference(servicoprestadoListServicoprestadoToAttach.getClass(), servicoprestadoListServicoprestadoToAttach.getServicoprestadoPK());
                attachedServicoprestadoList.add(servicoprestadoListServicoprestadoToAttach);
            }
            profissional.setServicoprestadoList(attachedServicoprestadoList);
            em.persist(profissional);
            for (Pedidoservico pedidoservicoListPedidoservico : profissional.getPedidoservicoList()) {
                Profissional oldIdprofissionalOfPedidoservicoListPedidoservico = pedidoservicoListPedidoservico.getIdprofissional();
                pedidoservicoListPedidoservico.setIdprofissional(profissional);
                pedidoservicoListPedidoservico = em.merge(pedidoservicoListPedidoservico);
                if (oldIdprofissionalOfPedidoservicoListPedidoservico != null) {
                    oldIdprofissionalOfPedidoservicoListPedidoservico.getPedidoservicoList().remove(pedidoservicoListPedidoservico);
                    oldIdprofissionalOfPedidoservicoListPedidoservico = em.merge(oldIdprofissionalOfPedidoservicoListPedidoservico);
                }
            }
            for (Agenda agendaListAgenda : profissional.getAgendaList()) {
                Profissional oldProfissionalOfAgendaListAgenda = agendaListAgenda.getProfissional();
                agendaListAgenda.setProfissional(profissional);
                agendaListAgenda = em.merge(agendaListAgenda);
                if (oldProfissionalOfAgendaListAgenda != null) {
                    oldProfissionalOfAgendaListAgenda.getAgendaList().remove(agendaListAgenda);
                    oldProfissionalOfAgendaListAgenda = em.merge(oldProfissionalOfAgendaListAgenda);
                }
            }
            for (Servicoprestado servicoprestadoListServicoprestado : profissional.getServicoprestadoList()) {
                Profissional oldProfissionalOfServicoprestadoListServicoprestado = servicoprestadoListServicoprestado.getProfissional();
                servicoprestadoListServicoprestado.setProfissional(profissional);
                servicoprestadoListServicoprestado = em.merge(servicoprestadoListServicoprestado);
                if (oldProfissionalOfServicoprestadoListServicoprestado != null) {
                    oldProfissionalOfServicoprestadoListServicoprestado.getServicoprestadoList().remove(servicoprestadoListServicoprestado);
                    oldProfissionalOfServicoprestadoListServicoprestado = em.merge(oldProfissionalOfServicoprestadoListServicoprestado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Profissional profissional) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profissional persistentProfissional = em.find(Profissional.class, profissional.getIdprofissional());
            List<Pedidoservico> pedidoservicoListOld = persistentProfissional.getPedidoservicoList();
            List<Pedidoservico> pedidoservicoListNew = profissional.getPedidoservicoList();
            List<Agenda> agendaListOld = persistentProfissional.getAgendaList();
            List<Agenda> agendaListNew = profissional.getAgendaList();
            List<Servicoprestado> servicoprestadoListOld = persistentProfissional.getServicoprestadoList();
            List<Servicoprestado> servicoprestadoListNew = profissional.getServicoprestadoList();
            List<String> illegalOrphanMessages = null;
            for (Pedidoservico pedidoservicoListOldPedidoservico : pedidoservicoListOld) {
                if (!pedidoservicoListNew.contains(pedidoservicoListOldPedidoservico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedidoservico " + pedidoservicoListOldPedidoservico + " since its idprofissional field is not nullable.");
                }
            }
            for (Agenda agendaListOldAgenda : agendaListOld) {
                if (!agendaListNew.contains(agendaListOldAgenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Agenda " + agendaListOldAgenda + " since its profissional field is not nullable.");
                }
            }
            for (Servicoprestado servicoprestadoListOldServicoprestado : servicoprestadoListOld) {
                if (!servicoprestadoListNew.contains(servicoprestadoListOldServicoprestado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Servicoprestado " + servicoprestadoListOldServicoprestado + " since its profissional field is not nullable.");
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
            profissional.setPedidoservicoList(pedidoservicoListNew);
            List<Agenda> attachedAgendaListNew = new ArrayList<Agenda>();
            for (Agenda agendaListNewAgendaToAttach : agendaListNew) {
                agendaListNewAgendaToAttach = em.getReference(agendaListNewAgendaToAttach.getClass(), agendaListNewAgendaToAttach.getAgendaPK());
                attachedAgendaListNew.add(agendaListNewAgendaToAttach);
            }
            agendaListNew = attachedAgendaListNew;
            profissional.setAgendaList(agendaListNew);
            List<Servicoprestado> attachedServicoprestadoListNew = new ArrayList<Servicoprestado>();
            for (Servicoprestado servicoprestadoListNewServicoprestadoToAttach : servicoprestadoListNew) {
                servicoprestadoListNewServicoprestadoToAttach = em.getReference(servicoprestadoListNewServicoprestadoToAttach.getClass(), servicoprestadoListNewServicoprestadoToAttach.getServicoprestadoPK());
                attachedServicoprestadoListNew.add(servicoprestadoListNewServicoprestadoToAttach);
            }
            servicoprestadoListNew = attachedServicoprestadoListNew;
            profissional.setServicoprestadoList(servicoprestadoListNew);
            profissional = em.merge(profissional);
            for (Pedidoservico pedidoservicoListNewPedidoservico : pedidoservicoListNew) {
                if (!pedidoservicoListOld.contains(pedidoservicoListNewPedidoservico)) {
                    Profissional oldIdprofissionalOfPedidoservicoListNewPedidoservico = pedidoservicoListNewPedidoservico.getIdprofissional();
                    pedidoservicoListNewPedidoservico.setIdprofissional(profissional);
                    pedidoservicoListNewPedidoservico = em.merge(pedidoservicoListNewPedidoservico);
                    if (oldIdprofissionalOfPedidoservicoListNewPedidoservico != null && !oldIdprofissionalOfPedidoservicoListNewPedidoservico.equals(profissional)) {
                        oldIdprofissionalOfPedidoservicoListNewPedidoservico.getPedidoservicoList().remove(pedidoservicoListNewPedidoservico);
                        oldIdprofissionalOfPedidoservicoListNewPedidoservico = em.merge(oldIdprofissionalOfPedidoservicoListNewPedidoservico);
                    }
                }
            }
            for (Agenda agendaListNewAgenda : agendaListNew) {
                if (!agendaListOld.contains(agendaListNewAgenda)) {
                    Profissional oldProfissionalOfAgendaListNewAgenda = agendaListNewAgenda.getProfissional();
                    agendaListNewAgenda.setProfissional(profissional);
                    agendaListNewAgenda = em.merge(agendaListNewAgenda);
                    if (oldProfissionalOfAgendaListNewAgenda != null && !oldProfissionalOfAgendaListNewAgenda.equals(profissional)) {
                        oldProfissionalOfAgendaListNewAgenda.getAgendaList().remove(agendaListNewAgenda);
                        oldProfissionalOfAgendaListNewAgenda = em.merge(oldProfissionalOfAgendaListNewAgenda);
                    }
                }
            }
            for (Servicoprestado servicoprestadoListNewServicoprestado : servicoprestadoListNew) {
                if (!servicoprestadoListOld.contains(servicoprestadoListNewServicoprestado)) {
                    Profissional oldProfissionalOfServicoprestadoListNewServicoprestado = servicoprestadoListNewServicoprestado.getProfissional();
                    servicoprestadoListNewServicoprestado.setProfissional(profissional);
                    servicoprestadoListNewServicoprestado = em.merge(servicoprestadoListNewServicoprestado);
                    if (oldProfissionalOfServicoprestadoListNewServicoprestado != null && !oldProfissionalOfServicoprestadoListNewServicoprestado.equals(profissional)) {
                        oldProfissionalOfServicoprestadoListNewServicoprestado.getServicoprestadoList().remove(servicoprestadoListNewServicoprestado);
                        oldProfissionalOfServicoprestadoListNewServicoprestado = em.merge(oldProfissionalOfServicoprestadoListNewServicoprestado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = profissional.getIdprofissional();
                if (findProfissional(id) == null) {
                    throw new NonexistentEntityException("The profissional with id " + id + " no longer exists.");
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
            Profissional profissional;
            try {
                profissional = em.getReference(Profissional.class, id);
                profissional.getIdprofissional();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profissional with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Pedidoservico> pedidoservicoListOrphanCheck = profissional.getPedidoservicoList();
            for (Pedidoservico pedidoservicoListOrphanCheckPedidoservico : pedidoservicoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Profissional (" + profissional + ") cannot be destroyed since the Pedidoservico " + pedidoservicoListOrphanCheckPedidoservico + " in its pedidoservicoList field has a non-nullable idprofissional field.");
            }
            List<Agenda> agendaListOrphanCheck = profissional.getAgendaList();
            for (Agenda agendaListOrphanCheckAgenda : agendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Profissional (" + profissional + ") cannot be destroyed since the Agenda " + agendaListOrphanCheckAgenda + " in its agendaList field has a non-nullable profissional field.");
            }
            List<Servicoprestado> servicoprestadoListOrphanCheck = profissional.getServicoprestadoList();
            for (Servicoprestado servicoprestadoListOrphanCheckServicoprestado : servicoprestadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Profissional (" + profissional + ") cannot be destroyed since the Servicoprestado " + servicoprestadoListOrphanCheckServicoprestado + " in its servicoprestadoList field has a non-nullable profissional field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(profissional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Profissional> findProfissionalEntities() {
        return findProfissionalEntities(true, -1, -1);
    }

    public List<Profissional> findProfissionalEntities(int maxResults, int firstResult) {
        return findProfissionalEntities(false, maxResults, firstResult);
    }

    private List<Profissional> findProfissionalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Profissional.class));
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

    public Profissional findProfissional(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profissional.class, id);
        } finally {
            em.close();
        }
    }

    public int getProfissionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profissional> rt = cq.from(Profissional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
