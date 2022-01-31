package ru.geekbrains.lesson9.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.lesson9.persist.Product;
import ru.geekbrains.lesson9.persist.ProductRepository;
import ru.geekbrains.lesson9.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public StringBuilder searchString(
            Optional<String> nameFilter,
            Optional<BigDecimal> minPriceFilter,
            Optional<BigDecimal> maxPriceFilter,
            Optional<Integer> page,
            Optional<Integer> size,
            Optional<String> sort,
            Optional<Boolean> desc
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        nameFilter.ifPresent(s -> sb.append("&nameFilter=").append(URLEncoder.encode(s)));
        minPriceFilter.ifPresent(bigDecimal -> sb.append("&minPriceFilter=").append(bigDecimal));
        maxPriceFilter.ifPresent(bigDecimal -> sb.append("&maxPriceFilter=").append(bigDecimal));
        page.ifPresent(integer -> sb.append("&page=").append(integer));
        size.ifPresent(integer -> sb.append("&size=").append(integer));
        sort.ifPresent(s -> sb.append("&sort=").append(s));
        desc.ifPresent(aBoolean -> sb.append("&desc=").append(aBoolean));

        return sb;
    }

    @GetMapping
    public String listPage(
        Model model,
        @RequestParam Optional<String> nameFilter,
        @RequestParam Optional<BigDecimal> minPriceFilter,
        @RequestParam Optional<BigDecimal> maxPriceFilter,
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size,
        @RequestParam Optional<String> sort,
        @RequestParam Optional<Boolean> desc
    ) {

        // Первый способ

//        List<Product> products = productRepository.findByFilterByQuery(
//                nameFilter.orElse(null),
//                minPriceFilter.orElse(null),
//                maxPriceFilter.orElse(null)
//        );

        // Второй способ

        Page<Product> products = productService.search(nameFilter, minPriceFilter, maxPriceFilter, page, size, sort, desc);

        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.get(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));

        return "product_form";

    }

    @DeleteMapping("/{id}")
    public String delete(
        @PathVariable("id") Long id,
        @RequestParam Optional<String> nameFilter,
        @RequestParam Optional<BigDecimal> minPriceFilter,
        @RequestParam Optional<BigDecimal> maxPriceFilter,
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size,
        @RequestParam Optional<String> sort,
        @RequestParam Optional<Boolean> desc
    ) {
        if (!productService.delete(id)) {
            throw new NotFoundException("Product with id = " + id + " not fount");
        }

        return "redirect:/product" + searchString(nameFilter, minPriceFilter, maxPriceFilter, page, size, sort, desc);
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult result,
           @RequestParam Optional<String> nameFilter,
           @RequestParam Optional<BigDecimal> minPriceFilter,
           @RequestParam Optional<BigDecimal> maxPriceFilter,
           @RequestParam Optional<Integer> page,
           @RequestParam Optional<Integer> size,
           @RequestParam Optional<String> sort,
           @RequestParam Optional<Boolean> desc
    ) {
        if (result.hasErrors()) {
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product" + searchString(nameFilter, minPriceFilter, maxPriceFilter, page, size, sort, desc);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
