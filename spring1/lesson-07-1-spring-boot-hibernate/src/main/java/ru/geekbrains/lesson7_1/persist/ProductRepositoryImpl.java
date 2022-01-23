package ru.geekbrains.lesson7_1.persist;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        return em.createQuery(query.select(root)).getResultList();
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    @Transactional
    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
            return product;
        }
        return em.merge(product);
    }

    @Transactional
    @Override
    public void delete(long id) {
        em.createQuery("delete from Product p where p.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
