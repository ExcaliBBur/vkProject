package com.example.vkproject.repository.api.implementations.user;

import com.example.vkproject.model.entity.Todos;
import com.example.vkproject.repository.api.interfaces.user.TodosRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.naming.ServiceUnavailableException;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class TodosRepositoryImpl implements TodosRepository {
    private final WebClient webClient;

    @Cacheable(value = "todos")
    public List<Todos> getUserTodos(Long userId) {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri(String.join("", "/users/", userId.toString(), "/todos"))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Todos[].class)
                .block()));
    }
}
