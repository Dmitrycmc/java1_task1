package ru.geekbrains.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    EntityManagerFactory emFactory;

    private interface MyInterface<T> {
        T doTx(EntityManager em);
    }

    private <T> T execInTx(MyInterface<T> i) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        T result = i.doTx(em);
        em.getTransaction().commit();
        em.close();
        return result;
    }

    private <T> T exec(MyInterface<T> i) {
        EntityManager em = emFactory.createEntityManager();
        T result = i.doTx(em);
        em.close();
        return result;
    }

    public ProductDaoImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public List<Product> findAll() {
        return exec(em -> em.createQuery("select p from Product p", Product.class).getResultList());
    }

    @Override
    public Optional<Product> findById(long id) {
        Product p = exec(em -> em.find(Product.class, id));

        return Optional.ofNullable(p);
    }

    @Override
    public void save(Product product) {
        if (product.getId() != null && findById(product.getId()).isPresent()) {
            execInTx(em -> em.merge(product));
        } else {
            execInTx(em -> {em.persist(product); return product;});
        }
    }

    @Override
    public void delete(long id) {
        execInTx(em -> em.createQuery("delete from Product where id = :id")
            .setParameter("id", id)
            .executeUpdate()
        );
    }
}
