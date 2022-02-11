package ru.geekbrains.lesson10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.geekbrains.lesson10.persist.User;
import ru.geekbrains.lesson10.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public StringBuilder searchString(
            Optional<Integer> page,
            Optional<Integer> size
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        page.ifPresent(integer -> sb.append("&page=").append(integer));
        size.ifPresent(integer -> sb.append("&size=").append(integer));

        return sb;
    }

    @GetMapping
    public String listPage(
        Model model,
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size
    ) {
        Page<User> users = userService.search(
                page,
                size
        );

        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.get(id)
                .orElseThrow(() -> new NotFoundException("User not found")));

        return "user_form";

    }

    @DeleteMapping("/{id}")
    public String delete(
        @PathVariable("id") Long id,
        @RequestParam Optional<Integer> page,
        @RequestParam Optional<Integer> size
    ) {
        if (!userService.delete(id)) {
            throw new NotFoundException("User with id = " + id + " not fount");
        }

        return "redirect:/user" + searchString(page, size);
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping
    public String save(@Valid User user, BindingResult result,
           @RequestParam Optional<Integer> page,
           @RequestParam Optional<Integer> size
    ) {
        if (result.hasErrors()) {
            return "user_form";
        }
        userService.save(user);
        return "redirect:/user" + searchString(page, size);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
