package ru.geekbrains.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Consumer;
import java.util.function.Function;

public class Dao {
    EntityManagerFactory emFactory;

    protected void execInTx(Consumer<EntityManager> i) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        try {
            i.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    protected  <T> T exec(Function<EntityManager, T> i) {
        EntityManager em = emFactory.createEntityManager();
        T result = i.apply(em);
        em.close();
        return result;
    }

    public Dao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }
}
