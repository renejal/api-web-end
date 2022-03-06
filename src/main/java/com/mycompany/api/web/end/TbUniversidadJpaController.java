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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author bjalvin
 */
public class TbUniversidadJpaController implements Serializable {

    public TbUniversidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TbUniversidad tbUniversidad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbEstudiante tbEstudiante = tbUniversidad.getTbEstudiante();
            if (tbEstudiante != null) {
                tbEstudiante = em.getReference(tbEstudiante.getClass(), tbEstudiante.getId());
                tbUniversidad.setTbEstudiante(tbEstudiante);
            }
            em.persist(tbUniversidad);
            if (tbEstudiante != null) {
                TbUniversidad oldTbUniversidadOfTbEstudiante = tbEstudiante.getTbUniversidad();
                if (oldTbUniversidadOfTbEstudiante != null) {
                    oldTbUniversidadOfTbEstudiante.setTbEstudiante(null);
                    oldTbUniversidadOfTbEstudiante = em.merge(oldTbUniversidadOfTbEstudiante);
                }
                tbEstudiante.setTbUniversidad(tbUniversidad);
                tbEstudiante = em.merge(tbEstudiante);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TbUniversidad tbUniversidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbUniversidad persistentTbUniversidad = em.find(TbUniversidad.class, tbUniversidad.getId());
            TbEstudiante tbEstudianteOld = persistentTbUniversidad.getTbEstudiante();
            TbEstudiante tbEstudianteNew = tbUniversidad.getTbEstudiante();
            List<String> illegalOrphanMessages = null;
            if (tbEstudianteOld != null && !tbEstudianteOld.equals(tbEstudianteNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain TbEstudiante " + tbEstudianteOld + " since its tbUniversidad field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tbEstudianteNew != null) {
                tbEstudianteNew = em.getReference(tbEstudianteNew.getClass(), tbEstudianteNew.getId());
                tbUniversidad.setTbEstudiante(tbEstudianteNew);
            }
            tbUniversidad = em.merge(tbUniversidad);
            if (tbEstudianteNew != null && !tbEstudianteNew.equals(tbEstudianteOld)) {
                TbUniversidad oldTbUniversidadOfTbEstudiante = tbEstudianteNew.getTbUniversidad();
                if (oldTbUniversidadOfTbEstudiante != null) {
                    oldTbUniversidadOfTbEstudiante.setTbEstudiante(null);
                    oldTbUniversidadOfTbEstudiante = em.merge(oldTbUniversidadOfTbEstudiante);
                }
                tbEstudianteNew.setTbUniversidad(tbUniversidad);
                tbEstudianteNew = em.merge(tbEstudianteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbUniversidad.getId();
                if (findTbUniversidad(id) == null) {
                    throw new NonexistentEntityException("The tbUniversidad with id " + id + " no longer exists.");
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
            TbUniversidad tbUniversidad;
            try {
                tbUniversidad = em.getReference(TbUniversidad.class, id);
                tbUniversidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbUniversidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            TbEstudiante tbEstudianteOrphanCheck = tbUniversidad.getTbEstudiante();
            if (tbEstudianteOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TbUniversidad (" + tbUniversidad + ") cannot be destroyed since the TbEstudiante " + tbEstudianteOrphanCheck + " in its tbEstudiante field has a non-nullable tbUniversidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tbUniversidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TbUniversidad> findTbUniversidadEntities() {
        return findTbUniversidadEntities(true, -1, -1);
    }

    public List<TbUniversidad> findTbUniversidadEntities(int maxResults, int firstResult) {
        return findTbUniversidadEntities(false, maxResults, firstResult);
    }

    private List<TbUniversidad> findTbUniversidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TbUniversidad.class));
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

    public TbUniversidad findTbUniversidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TbUniversidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbUniversidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TbUniversidad> rt = cq.from(TbUniversidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
