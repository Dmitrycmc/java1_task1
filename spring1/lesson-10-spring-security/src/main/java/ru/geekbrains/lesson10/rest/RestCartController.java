package ru.geekbrains.lesson10.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.lesson10.controller.NotFoundException;
import ru.geekbrains.lesson10.persist.Product;
import ru.geekbrains.lesson10.service.ProductService;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
public class RestCartController {

    private final ProductService productService;

    @Autowired
    public RestCartController(ProductService productService) {
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
                desc,
                Optional.of(true)
        );

    }

    @PostMapping("add/{id}")
    public Product inc(@PathVariable Long id, @RequestParam(defaultValue = "1") int count) {
        Product product = productService.get(id)
                .orElseThrow(() -> new NotFoundException("Product with id = " + id + " not fount"));

        product.setSelected(product.getSelected() + count);
        productService.save(product);

        return product;
    }

    @PostMapping("sub/{id}")
    public Product dec(@PathVariable Long id, @RequestParam(defaultValue = "1") int count) {
        Product product = productService.get(id)
                .orElseThrow(() -> new NotFoundException("Product with id = " + id + " not fount"));

        if (product.getSelected() < count) {
            throw new IllegalArgumentException("Count = " + count + " more than items in cart = " + product.getSelected());
        }

        product.setSelected(product.getSelected() - count);
        productService.save(product);

        return product;
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable Long id) {
        Product product = productService.get(id)
                .orElseThrow(() -> new NotFoundException("Product with id = " + id + " not fount"));

        product.setSelected(0);
        productService.save(product);

        return product;
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
    public ErrorDto RuntimeExceptionHandler(RuntimeException e) {
        return new ErrorDto(e.getMessage());
    }
}
