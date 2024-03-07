package com.example.vkproject.repository;

import com.example.vkproject.model.entity.Comment;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class CommentRepository {
    private final WebClient webClient;

    public List<Comment> getCommentsByPostId(Long postId) {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri(String.join("", "/posts/", postId.toString(), "/comments"))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(Comment[].class)
                .block()));
    }
}