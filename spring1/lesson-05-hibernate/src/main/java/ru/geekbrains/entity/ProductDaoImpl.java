package ru.geekbrains.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    EntityManagerFactory emFactory;

    public ProductDaoImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public List<Product> findAll() {
        EntityManager em = emFactory.createEntityManager();

        List<Product> result = em.createQuery("select p from Product p", Product.class).getResultList();
        em.close();
        return result;
    }

    @Override
    public Optional<Product> findById(long id) {
        System.out.println(id);
        EntityManager em = emFactory.createEntityManager();

        Product p = em.find(Product.class, id);

        em.close();

        return Optional.ofNullable(p);
    }

    @Override
    public void save(Product product) {
        EntityManager em = emFactory.createEntityManager();

        if (product.getId() != null && findById(product.getId()).isPresent()) {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } else {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        }

        em.close();
    }

    @Override
    public void delete(long id) {
        EntityManager em = emFactory.createEntityManager();

        em.getTransaction().begin();

        em.createQuery("delete from Product where id = :id")
                .setParameter("id", id)
                .executeUpdate();

        em.getTransaction().commit();

        em.close();
    }
}
