package com.example.vkproject.repository.api.implementations.album;

import com.example.vkproject.dto.album.AlbumRequest;
import com.example.vkproject.model.entity.Album;
import com.example.vkproject.repository.api.interfaces.album.AlbumRepository;
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
public class AlbumRepositoryImpl implements AlbumRepository {
    private final WebClient webClient;

    @Cacheable(value = "albums")
    public List<Album> getAlbums() {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri("/albums")
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Album[].class)
                .block()));
    }
    @Cacheable(value = "albums", key = "#id")
    public Album getAlbum(Long id) {
        return webClient
                .get()
                .uri(String.join("", "/albums/", id.toString()))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new EntityNotFoundException("Такого альбома не существует")))
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Album.class)
                .block();
    }

    @CachePut(value = "albums")
    public Album createAlbum(AlbumRequest album) {
        return webClient
                .post()
                .uri("/albums")
                .body(BodyInserters.fromValue(album))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Album.class)
                .block();
    }

    @CachePut(value = "albums", key = "#id")
    public Album updateAlbum(AlbumRequest album, Long id) {
        return webClient
                .patch()
                .uri(String.join("", "/albums/", id.toString()))
                .body(BodyInserters.fromValue(album))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Album.class)
                .block();
    }

    @CacheEvict(value = "albums")
    public void deleteAlbum(Long id) {
        webClient
                .delete()
                .uri(String.join("", "/albums/", id.toString()))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Album.class)
                .block();
    }

    @Cacheable(value = "albums")
    public List<Album> getUserAlbums(Long userId) {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri(String.join("", "/users/", userId.toString(), "/albums"))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Album[].class)
                .block()));
    }
}
