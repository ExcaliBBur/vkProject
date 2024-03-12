package com.example.vkproject.repository.api.implementations.post;

import com.example.vkproject.dto.post.PostRequest;
import com.example.vkproject.model.entity.Post;
import com.example.vkproject.repository.api.interfaces.post.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.naming.ServiceUnavailableException;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final WebClient webClient;

    @Cacheable(value = "posts")
    public List<Post> getPosts() {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri("/posts")
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Post[].class)
                .block()));
    }

    @Cacheable(value = "posts")
    public Post getPost(Long id) {
        return webClient
                .get()
                .uri(String.join("", "/posts/", id.toString()))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new EntityNotFoundException("Такого поста не существует")))
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Post.class)
                .block();
    }

    @CachePut(value = "posts")
    public Post createPost(PostRequest post) {
        return webClient
                .post()
                .uri("/posts")
                .body(BodyInserters.fromValue(post))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Post.class)
                .block();
    }

    @CachePut(value = "posts", key = "#id")
    public Post updatePost(PostRequest post, Long id) {
        return webClient
                .patch()
                .uri(String.join("", "/posts/", id.toString()))
                .body(BodyInserters.fromValue(post))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Post.class)
                .block();
    }

    @CacheEvict(value = "posts")
    public void deletePost(Long id) {
        webClient
                .delete()
                .uri(String.join("", "/posts/", id.toString()))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Post.class)
                .block();
    }

    @Cacheable(value = "posts")
    public List<Post> getUserPosts(Long userId) {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri(String.join("", "/users/", userId.toString(), "/posts"))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Post[].class)
                .block()));
    }
}
