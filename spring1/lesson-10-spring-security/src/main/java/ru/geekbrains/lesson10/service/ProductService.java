package ru.geekbrains.lesson10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson10.persist.Product;
import ru.geekbrains.lesson10.persist.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> search(
            Optional<String> nameFilter,
            Optional<BigDecimal> minPriceFilter,
            Optional<BigDecimal> maxPriceFilter,
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<String> sort,
            Optional<Boolean> desc,
            Optional<Boolean> isInCart
    ) {

        // Первый способ

        //        List<Product> products = productRepository.findByFilterByQuery(
        //                nameFilter.orElse(null),
        //                minPriceFilter.orElse(null),
        //                maxPriceFilter.orElse(null)
        //        );

        // Второй способ

        return productRepository.findAll(Specification.<Product>where(null)
                        .and(nameFilter.<Specification<Product>>map(s -> (root, query, cb) -> cb.like(root.get("name"), "%" + s + "%")).orElse(null))
                        .and(minPriceFilter.<Specification<Product>>map(s -> (root, query, cb) -> cb.ge(root.get("price"), s)).orElse(null))
                        .and(maxPriceFilter.<Specification<Product>>map(s -> (root, query, cb) -> cb.le(root.get("price"), s)).orElse(null))
                        .and(isInCart.<Specification<Product>>map(s -> (root, query, cb) -> cb.gt(root.get("selected"), 0)).orElse(null)),
                PageRequest.of(
                        page.orElse(1) - 1,
                        size.orElse(5),
                        Sort.by(
                                desc.orElse(false) ? Sort.Direction.DESC : Sort.Direction.ASC,
                                sort.filter(s -> !s.isEmpty()).orElse("id")
                        )
                )
        );
    }

    public Optional<Product> get(Long id) {
        return productRepository.findById(id);
    }

    public boolean delete(Long id) {
        if (!productRepository.findById(id).isPresent()) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
