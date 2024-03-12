package com.example.vkproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class VkProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(VkProjectApplication.class, args);
    }

}
