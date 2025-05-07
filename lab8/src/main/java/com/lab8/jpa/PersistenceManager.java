package com.lab8.jpa;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager {
    private static final PersistenceManager instance = new PersistenceManager();
    private final EntityManagerFactory emf;

    private PersistenceManager() {
        emf = Persistence.createEntityManagerFactory("CitiesPU");
    }

    public static PersistenceManager getInstance() {
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}