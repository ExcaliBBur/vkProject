package com.example.vkproject.repository;

import com.example.vkproject.dto.PostRequest;
import com.example.vkproject.model.Post;
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
public class PostRepository {
    private final WebClient webClient;

    public List<Post> getPosts() {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri("/posts")
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(Post[].class)
                .block()));
    }

    public Post getPost(Long id) {
        return webClient
                .get()
                .uri(String.join("", "/posts/", id.toString()))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new EntityNotFoundException("Такого поста не существует")))
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(Post.class)
                .block();
    }

    public Post createPost(PostRequest post) {
        return webClient
                .post()
                .uri("/posts")
                .body(BodyInserters.fromValue(post))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(Post.class)
                .block();
    }

    public Post updatePost(PostRequest post, Long id) {
        return webClient
                .patch()
                .uri(String.join("", "/posts/", id.toString()))
                .body(BodyInserters.fromValue(post))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(Post.class)
                .block();
    }

    public void deletePost(Long id) {
        webClient
                .delete()
                .uri(String.join("", "/posts/", id.toString()))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(Post.class)
                .block();
    }
}
