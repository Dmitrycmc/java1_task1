package ru.geekbrains.lesson7_2.controller;

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
import ru.geekbrains.lesson7_2.persist.Product;
import ru.geekbrains.lesson7_2.persist.ProductRepository;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

        Page<Product> products = productRepository.findAll(Specification.<Product>where(null)
                .and(nameFilter.<Specification<Product>>map(s -> (root, query, cb) -> cb.like(root.get("name"), "%" + s + "%")).orElse(null))
                .and(minPriceFilter.<Specification<Product>>map(s -> (root, query, cb) -> cb.ge(root.get("price"), s)).orElse(null))
                .and(maxPriceFilter.<Specification<Product>>map(s -> (root, query, cb) -> cb.le(root.get("price"), s)).orElse(null)),
                PageRequest.of(
                        page.orElse(1) - 1,
                        size.orElse(5),
                        Sort.by(
                                desc.orElse(false) ? Sort.Direction.DESC : Sort.Direction.ASC,
                                sort.filter(s -> !s.isEmpty()).orElse("id")
                        )
                )
        );

        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
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
        productRepository.deleteById(id);
        StringBuilder sb = new StringBuilder();
        sb.append("redirect:/product?");
        System.out.println(nameFilter);
        nameFilter.ifPresent(s -> sb.append("&nameFilter=").append(URLEncoder.encode(s)));
        minPriceFilter.ifPresent(bigDecimal -> sb.append("&minPriceFilter=").append(bigDecimal));
        maxPriceFilter.ifPresent(bigDecimal -> sb.append("&maxPriceFilter=").append(bigDecimal));
        page.ifPresent(integer -> sb.append("&page=").append(integer));
        size.ifPresent(integer -> sb.append("&size=").append(integer));
        sort.ifPresent(s -> sb.append("&sort=").append(s));
        desc.ifPresent(aBoolean -> sb.append("&desc=").append(aBoolean));
        System.out.println(sb.toString());
        return sb.toString();
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
