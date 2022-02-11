package ru.geekbrains.lesson10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson10.persist.User;
import ru.geekbrains.lesson10.persist.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> search(
            Optional<Integer> page,
            Optional<Integer> size
    ) {
        return userRepository.findAll(PageRequest.of(
                        page.orElse(1) - 1,
                        size.orElse(5)
                )
        );
    }

    public Optional<User> get(Long id) {
        return userRepository.findById(id);
    }

    public boolean delete(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
