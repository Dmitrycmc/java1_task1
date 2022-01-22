package ru.geekbrains.entity.product;

import org.springframework.stereotype.Component;
import ru.geekbrains.entity.Dao;
import ru.geekbrains.entity.customer.Customer;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoImpl extends Dao implements ProductDao {
    public ProductDaoImpl(EntityManagerFactory emFactory) {
        super(emFactory);
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
            execInTx(em -> em.persist(product));
        }
    }

    @Override
    public void delete(long id) {
        execInTx(em -> em.createQuery("delete from Product where id = :id")
            .setParameter("id", id)
            .executeUpdate()
        );
    }

    @Override
    public List<Customer> getProductCustomers(Product product) {
        return exec(em -> em.createQuery("select distinct c from Product p join p.customerProducts cp join cp.customer c where p.id = :id", Customer.class).setParameter("id", product.getId()).getResultList());
    }
}
