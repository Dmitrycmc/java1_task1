package ru.geekbrains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan("ru.geekbrains")
public class SpringConfig {
    @Bean
    EntityManagerFactory emFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml").buildSessionFactory();
    }
}
