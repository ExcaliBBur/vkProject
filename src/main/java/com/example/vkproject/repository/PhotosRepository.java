package com.example.vkproject.repository;

import com.example.vkproject.model.entity.Photo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class PhotosRepository {
    private final WebClient webClient;

    public List<Photo> getPhotosByAlbumId(Long albumId) {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri(String.join("", "/albums/", albumId.toString(), "/photos"))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("API не отвечает")))
                .bodyToMono(Photo[].class)
                .block()));
    }
}
