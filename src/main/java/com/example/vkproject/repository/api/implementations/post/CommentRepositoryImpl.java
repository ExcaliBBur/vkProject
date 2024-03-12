package com.example.vkproject.repository.api.implementations.post;

import com.example.vkproject.model.entity.Comment;
import com.example.vkproject.repository.api.interfaces.post.CommentRepository;
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
public class CommentRepositoryImpl implements CommentRepository {
    private final WebClient webClient;

    @Cacheable(value = "comments")
    public List<Comment> getCommentsByPostId(Long postId) {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri(String.join("", "/posts/", postId.toString(), "/comments"))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Comment[].class)
                .block()));
    }
}
