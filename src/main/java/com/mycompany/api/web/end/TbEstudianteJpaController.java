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
public class TbEstudianteJpaController implements Serializable {
    
    

    public TbEstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TbEstudiante tbEstudiante) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        TbUniversidad tbUniversidadOrphanCheck = tbEstudiante.getTbUniversidad();
        if (tbUniversidadOrphanCheck != null) {
            TbEstudiante oldTbEstudianteOfTbUniversidad = tbUniversidadOrphanCheck.getTbEstudiante();
            if (oldTbEstudianteOfTbUniversidad != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The TbUniversidad " + tbUniversidadOrphanCheck + " already has an item of type TbEstudiante whose tbUniversidad column cannot be null. Please make another selection for the tbUniversidad field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbUniversidad tbUniversidad = tbEstudiante.getTbUniversidad();
            if (tbUniversidad != null) {
                tbUniversidad = em.getReference(tbUniversidad.getClass(), tbUniversidad.getId());
                tbEstudiante.setTbUniversidad(tbUniversidad);
            }
            em.persist(tbEstudiante);
            if (tbUniversidad != null) {
                tbUniversidad.setTbEstudiante(tbEstudiante);
                tbUniversidad = em.merge(tbUniversidad);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TbEstudiante tbEstudiante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbEstudiante persistentTbEstudiante = em.find(TbEstudiante.class, tbEstudiante.getId());
            TbUniversidad tbUniversidadOld = persistentTbEstudiante.getTbUniversidad();
            TbUniversidad tbUniversidadNew = tbEstudiante.getTbUniversidad();
            List<String> illegalOrphanMessages = null;
            if (tbUniversidadNew != null && !tbUniversidadNew.equals(tbUniversidadOld)) {
                TbEstudiante oldTbEstudianteOfTbUniversidad = tbUniversidadNew.getTbEstudiante();
                if (oldTbEstudianteOfTbUniversidad != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The TbUniversidad " + tbUniversidadNew + " already has an item of type TbEstudiante whose tbUniversidad column cannot be null. Please make another selection for the tbUniversidad field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tbUniversidadNew != null) {
                tbUniversidadNew = em.getReference(tbUniversidadNew.getClass(), tbUniversidadNew.getId());
                tbEstudiante.setTbUniversidad(tbUniversidadNew);
            }
            tbEstudiante = em.merge(tbEstudiante);
            if (tbUniversidadOld != null && !tbUniversidadOld.equals(tbUniversidadNew)) {
                tbUniversidadOld.setTbEstudiante(null);
                tbUniversidadOld = em.merge(tbUniversidadOld);
            }
            if (tbUniversidadNew != null && !tbUniversidadNew.equals(tbUniversidadOld)) {
                tbUniversidadNew.setTbEstudiante(tbEstudiante);
                tbUniversidadNew = em.merge(tbUniversidadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbEstudiante.getId();
                if (findTbEstudiante(id) == null) {
                    throw new NonexistentEntityException("The tbEstudiante with id " + id + " no longer exists.");
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
            TbEstudiante tbEstudiante;
            try {
                tbEstudiante = em.getReference(TbEstudiante.class, id);
                tbEstudiante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbEstudiante with id " + id + " no longer exists.", enfe);
            }
            TbUniversidad tbUniversidad = tbEstudiante.getTbUniversidad();
            if (tbUniversidad != null) {
                tbUniversidad.setTbEstudiante(null);
                tbUniversidad = em.merge(tbUniversidad);
            }
            em.remove(tbEstudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TbEstudiante> findTbEstudianteEntities() {
        return findTbEstudianteEntities(true, -1, -1);
    }

    public List<TbEstudiante> findTbEstudianteEntities(int maxResults, int firstResult) {
        return findTbEstudianteEntities(false, maxResults, firstResult);
    }

    private List<TbEstudiante> findTbEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TbEstudiante.class));
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

    public TbEstudiante findTbEstudiante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TbEstudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TbEstudiante> rt = cq.from(TbEstudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
