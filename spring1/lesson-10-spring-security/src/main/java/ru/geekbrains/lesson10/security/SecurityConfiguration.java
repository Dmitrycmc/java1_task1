package ru.geekbrains.lesson10.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    public void authConfig(AuthenticationManagerBuilder auth, PasswordEncoder encoder, UserDetailsService userDetailService) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root")
                .password(encoder.encode("root"))
                .roles("SUPER_ADMIN");

        auth.userDetailsService(userDetailService);

    }
}
