package ru.geekbrains.entity.customer_product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class CustomerProductDaoImpl implements CustomerProductDao {
    EntityManagerFactory emFactory;

    private void execInTx(Consumer<EntityManager> i) {
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

    private <T> T exec(Function<EntityManager, T> i) {
        EntityManager em = emFactory.createEntityManager();
        T result = i.apply(em);
        em.close();
        return result;
    }

    public CustomerProductDaoImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public List<CustomerProduct> findAll() {
        return exec(em -> em.createQuery("select p from Customer p", CustomerProduct.class).getResultList());
    }

    @Override
    public Optional<CustomerProduct> findById(long id) {
        CustomerProduct p = exec(em -> em.find(CustomerProduct.class, id));

        return Optional.ofNullable(p);
    }

    @Override
    public void save(CustomerProduct product) {
        if (product.getId() != null && findById(product.getId()).isPresent()) {
            execInTx(em -> em.merge(product));
        } else {
            execInTx(em -> em.persist(product));
        }
    }

    @Override
    public void delete(long id) {
        execInTx(em -> em.createQuery("delete from Customer where id = :id")
            .setParameter("id", id)
            .executeUpdate()
        );
    }
}
