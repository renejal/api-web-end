/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.api.web.end;

import com.mycompany.api.web.end.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author bjalvin
 */
public class TbEstudianteCursoJpaController implements Serializable {

    public TbEstudianteCursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TbEstudianteCurso tbEstudianteCurso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbCurso idCurso = tbEstudianteCurso.getIdCurso();
            if (idCurso != null) {
                idCurso = em.getReference(idCurso.getClass(), idCurso.getId());
                tbEstudianteCurso.setIdCurso(idCurso);
            }
            em.persist(tbEstudianteCurso);
            if (idCurso != null) {
                idCurso.getTbEstudianteCursoCollection().add(tbEstudianteCurso);
                idCurso = em.merge(idCurso);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TbEstudianteCurso tbEstudianteCurso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbEstudianteCurso persistentTbEstudianteCurso = em.find(TbEstudianteCurso.class, tbEstudianteCurso.getId());
            TbCurso idCursoOld = persistentTbEstudianteCurso.getIdCurso();
            TbCurso idCursoNew = tbEstudianteCurso.getIdCurso();
            if (idCursoNew != null) {
                idCursoNew = em.getReference(idCursoNew.getClass(), idCursoNew.getId());
                tbEstudianteCurso.setIdCurso(idCursoNew);
            }
            tbEstudianteCurso = em.merge(tbEstudianteCurso);
            if (idCursoOld != null && !idCursoOld.equals(idCursoNew)) {
                idCursoOld.getTbEstudianteCursoCollection().remove(tbEstudianteCurso);
                idCursoOld = em.merge(idCursoOld);
            }
            if (idCursoNew != null && !idCursoNew.equals(idCursoOld)) {
                idCursoNew.getTbEstudianteCursoCollection().add(tbEstudianteCurso);
                idCursoNew = em.merge(idCursoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbEstudianteCurso.getId();
                if (findTbEstudianteCurso(id) == null) {
                    throw new NonexistentEntityException("The tbEstudianteCurso with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbEstudianteCurso tbEstudianteCurso;
            try {
                tbEstudianteCurso = em.getReference(TbEstudianteCurso.class, id);
                tbEstudianteCurso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbEstudianteCurso with id " + id + " no longer exists.", enfe);
            }
            TbCurso idCurso = tbEstudianteCurso.getIdCurso();
            if (idCurso != null) {
                idCurso.getTbEstudianteCursoCollection().remove(tbEstudianteCurso);
                idCurso = em.merge(idCurso);
            }
            em.remove(tbEstudianteCurso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TbEstudianteCurso> findTbEstudianteCursoEntities() {
        return findTbEstudianteCursoEntities(true, -1, -1);
    }

    public List<TbEstudianteCurso> findTbEstudianteCursoEntities(int maxResults, int firstResult) {
        return findTbEstudianteCursoEntities(false, maxResults, firstResult);
    }

    private List<TbEstudianteCurso> findTbEstudianteCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TbEstudianteCurso.class));
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

    public TbEstudianteCurso findTbEstudianteCurso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TbEstudianteCurso.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbEstudianteCursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TbEstudianteCurso> rt = cq.from(TbEstudianteCurso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
