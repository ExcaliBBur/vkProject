package com.example.vkproject.repository;

import com.example.vkproject.model.entity.Todos;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class TodosRepository {
    private final WebClient webClient;

    public List<Todos> getUserTodos(Long userId) {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri(String.join("", "/users/", userId.toString(), "/todos"))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(Todos[].class)
                .block()));
    }
}
