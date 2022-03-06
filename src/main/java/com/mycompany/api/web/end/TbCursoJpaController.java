/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.api.web.end;

import com.mycompany.api.web.end.exceptions.IllegalOrphanException;
import com.mycompany.api.web.end.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bjalvin
 */
public class TbCursoJpaController implements Serializable {

    public TbCursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TbCurso tbCurso) {
        if (tbCurso.getTbEstudianteCursoCollection() == null) {
            tbCurso.setTbEstudianteCursoCollection(new ArrayList<TbEstudianteCurso>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TbEstudianteCurso> attachedTbEstudianteCursoCollection = new ArrayList<TbEstudianteCurso>();
            for (TbEstudianteCurso tbEstudianteCursoCollectionTbEstudianteCursoToAttach : tbCurso.getTbEstudianteCursoCollection()) {
                tbEstudianteCursoCollectionTbEstudianteCursoToAttach = em.getReference(tbEstudianteCursoCollectionTbEstudianteCursoToAttach.getClass(), tbEstudianteCursoCollectionTbEstudianteCursoToAttach.getId());
                attachedTbEstudianteCursoCollection.add(tbEstudianteCursoCollectionTbEstudianteCursoToAttach);
            }
            tbCurso.setTbEstudianteCursoCollection(attachedTbEstudianteCursoCollection);
            em.persist(tbCurso);
            for (TbEstudianteCurso tbEstudianteCursoCollectionTbEstudianteCurso : tbCurso.getTbEstudianteCursoCollection()) {
                TbCurso oldIdCursoOfTbEstudianteCursoCollectionTbEstudianteCurso = tbEstudianteCursoCollectionTbEstudianteCurso.getIdCurso();
                tbEstudianteCursoCollectionTbEstudianteCurso.setIdCurso(tbCurso);
                tbEstudianteCursoCollectionTbEstudianteCurso = em.merge(tbEstudianteCursoCollectionTbEstudianteCurso);
                if (oldIdCursoOfTbEstudianteCursoCollectionTbEstudianteCurso != null) {
                    oldIdCursoOfTbEstudianteCursoCollectionTbEstudianteCurso.getTbEstudianteCursoCollection().remove(tbEstudianteCursoCollectionTbEstudianteCurso);
                    oldIdCursoOfTbEstudianteCursoCollectionTbEstudianteCurso = em.merge(oldIdCursoOfTbEstudianteCursoCollectionTbEstudianteCurso);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TbCurso tbCurso) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbCurso persistentTbCurso = em.find(TbCurso.class, tbCurso.getId());
            Collection<TbEstudianteCurso> tbEstudianteCursoCollectionOld = persistentTbCurso.getTbEstudianteCursoCollection();
            Collection<TbEstudianteCurso> tbEstudianteCursoCollectionNew = tbCurso.getTbEstudianteCursoCollection();
            List<String> illegalOrphanMessages = null;
            for (TbEstudianteCurso tbEstudianteCursoCollectionOldTbEstudianteCurso : tbEstudianteCursoCollectionOld) {
                if (!tbEstudianteCursoCollectionNew.contains(tbEstudianteCursoCollectionOldTbEstudianteCurso)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TbEstudianteCurso " + tbEstudianteCursoCollectionOldTbEstudianteCurso + " since its idCurso field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TbEstudianteCurso> attachedTbEstudianteCursoCollectionNew = new ArrayList<TbEstudianteCurso>();
            for (TbEstudianteCurso tbEstudianteCursoCollectionNewTbEstudianteCursoToAttach : tbEstudianteCursoCollectionNew) {
                tbEstudianteCursoCollectionNewTbEstudianteCursoToAttach = em.getReference(tbEstudianteCursoCollectionNewTbEstudianteCursoToAttach.getClass(), tbEstudianteCursoCollectionNewTbEstudianteCursoToAttach.getId());
                attachedTbEstudianteCursoCollectionNew.add(tbEstudianteCursoCollectionNewTbEstudianteCursoToAttach);
            }
            tbEstudianteCursoCollectionNew = attachedTbEstudianteCursoCollectionNew;
            tbCurso.setTbEstudianteCursoCollection(tbEstudianteCursoCollectionNew);
            tbCurso = em.merge(tbCurso);
            for (TbEstudianteCurso tbEstudianteCursoCollectionNewTbEstudianteCurso : tbEstudianteCursoCollectionNew) {
                if (!tbEstudianteCursoCollectionOld.contains(tbEstudianteCursoCollectionNewTbEstudianteCurso)) {
                    TbCurso oldIdCursoOfTbEstudianteCursoCollectionNewTbEstudianteCurso = tbEstudianteCursoCollectionNewTbEstudianteCurso.getIdCurso();
                    tbEstudianteCursoCollectionNewTbEstudianteCurso.setIdCurso(tbCurso);
                    tbEstudianteCursoCollectionNewTbEstudianteCurso = em.merge(tbEstudianteCursoCollectionNewTbEstudianteCurso);
                    if (oldIdCursoOfTbEstudianteCursoCollectionNewTbEstudianteCurso != null && !oldIdCursoOfTbEstudianteCursoCollectionNewTbEstudianteCurso.equals(tbCurso)) {
                        oldIdCursoOfTbEstudianteCursoCollectionNewTbEstudianteCurso.getTbEstudianteCursoCollection().remove(tbEstudianteCursoCollectionNewTbEstudianteCurso);
                        oldIdCursoOfTbEstudianteCursoCollectionNewTbEstudianteCurso = em.merge(oldIdCursoOfTbEstudianteCursoCollectionNewTbEstudianteCurso);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbCurso.getId();
                if (findTbCurso(id) == null) {
                    throw new NonexistentEntityException("The tbCurso with id " + id + " no longer exists.");
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
            TbCurso tbCurso;
            try {
                tbCurso = em.getReference(TbCurso.class, id);
                tbCurso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbCurso with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TbEstudianteCurso> tbEstudianteCursoCollectionOrphanCheck = tbCurso.getTbEstudianteCursoCollection();
            for (TbEstudianteCurso tbEstudianteCursoCollectionOrphanCheckTbEstudianteCurso : tbEstudianteCursoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TbCurso (" + tbCurso + ") cannot be destroyed since the TbEstudianteCurso " + tbEstudianteCursoCollectionOrphanCheckTbEstudianteCurso + " in its tbEstudianteCursoCollection field has a non-nullable idCurso field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tbCurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TbCurso> findTbCursoEntities() {
        return findTbCursoEntities(true, -1, -1);
    }

    public List<TbCurso> findTbCursoEntities(int maxResults, int firstResult) {
        return findTbCursoEntities(false, maxResults, firstResult);
    }

    private List<TbCurso> findTbCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TbCurso.class));
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

    public TbCurso findTbCurso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TbCurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbCursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TbCurso> rt = cq.from(TbCurso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
