package ru.geekbrains.entity.customer;

import ru.geekbrains.entity.Dao;
import ru.geekbrains.entity.customer_product.CustomerProduct;
import ru.geekbrains.entity.product.Product;

import javax.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CustomerDaoImpl extends Dao implements CustomerDao {
    public CustomerDaoImpl(EntityManagerFactory emFactory) {
        super(emFactory);
    }

    @Override
    public List<Customer> findAll() {
        return exec(em -> em.createQuery("select p from Customer p", Customer.class).getResultList());
    }

    @Override
    public Optional<Customer> findById(long id) {
        Customer p = exec(em -> em.find(Customer.class, id));

        return Optional.ofNullable(p);
    }

    @Override
    public void save(Customer product) {
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

    @Override
    public void savePurchases(Customer customer, Product[] products) {
        execInTx(em -> {
            Arrays.stream(products).forEach(product -> em.persist(new CustomerProduct(customer, product)));
        });
    }
}
