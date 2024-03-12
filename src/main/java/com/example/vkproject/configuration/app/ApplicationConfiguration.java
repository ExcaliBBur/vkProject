package com.example.vkproject.configuration.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Map;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Map<String, String> violationsMap() {
        return Map.of(
                "users_username_key", "Такой пользователь уже существует"
        );
    }

}