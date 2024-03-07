package com.example.vkproject.repository;

import com.example.vkproject.dto.user.UserRequest;
import com.example.vkproject.model.entity.Post;
import com.example.vkproject.model.entity.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final WebClient webClient;
    public List<User> getUsers() {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri("/users")
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(User[].class)
                .block()));
    }

    public User getUser(Long id) {
        return webClient
                .get()
                .uri(String.join("", "/users/", id.toString()))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new EntityNotFoundException("Такого пользователя не существует")))
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(User.class)
                .block();
    }

    public User createUser(UserRequest user) {
        return webClient
                .post()
                .uri("/users")
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(User.class)
                .block();
    }

    public User updateUser(UserRequest user, Long id) {
        return webClient
                .patch()
                .uri(String.join("", "/users/", id.toString()))
                .body(BodyInserters.fromValue(user))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(User.class)
                .block();
    }

    public void deleteUser(Long id) {
        webClient
                .delete()
                .uri(String.join("", "/users/", id.toString()))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(User.class)
                .block();
    }
}
