package com.lab8.repository;

import java.util.List;

import com.lab8.jpa.PersistenceManager;
import com.lab8.model.City;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CityRepository {

    public void create(City city) {
        EntityManager em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(city);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public City findById(int id) {
        EntityManager em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        try {
            return em.find(City.class, id);
        } finally {
            em.close();
        }
    }

    public List<City> findByName(String pattern) {
        EntityManager em = PersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
        try {
            TypedQuery<City> query = em.createNamedQuery("City.findByName", City.class);
            query.setParameter("name", "%" + pattern + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}