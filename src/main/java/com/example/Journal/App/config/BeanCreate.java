package com.example.Journal.App.config;

import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanCreate {
    @Bean
    @PreDestroy
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
