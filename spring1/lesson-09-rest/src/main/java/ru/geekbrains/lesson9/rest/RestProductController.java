package ru.geekbrains.lesson9.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.lesson9.controller.NotFoundException;
import ru.geekbrains.lesson9.persist.Product;
import ru.geekbrains.lesson9.service.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class RestProductController {

    private final ProductService productService;

    @Autowired
    public RestProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public Page<Product> search(
        @RequestParam Optional<String> nameFilter,
        @RequestParam Optional<BigDecimal> minPriceFilter,
        @RequestParam Optional<BigDecimal> maxPriceFilter,
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size,
        @RequestParam Optional<String> sort,
        @RequestParam Optional<Boolean> desc
    ) {
        return productService.search(
                nameFilter,
                minPriceFilter,
                maxPriceFilter,
                page,
                size,
                sort,
                desc
        );

    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return productService.get(id).orElseThrow(() -> new NotFoundException("Product with id = " + id + " not fount"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (!productService.delete(id)) {
            throw new NotFoundException("Product with id = " + id + " not fount");
        }
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Id must be null");
        }
        return productService.save(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Id must not be null");
        }
        productService.get(product.getId())
                .orElseThrow(() -> new NotFoundException("Product with id = " + product.getId() + " not fount"));

        return productService.save(product);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto notFoundExceptionHandler(NotFoundException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return new ErrorDto(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto illegalArgumentExceptionHandler(RuntimeException e) {
        return new ErrorDto(e.getMessage());
    }
}
