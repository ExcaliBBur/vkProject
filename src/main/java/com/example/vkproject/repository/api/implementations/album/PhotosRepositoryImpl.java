package com.example.vkproject.repository.api.implementations.album;

import com.example.vkproject.model.entity.Photo;
import com.example.vkproject.repository.api.interfaces.album.PhotosRepository;
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
public class PhotosRepositoryImpl implements PhotosRepository {
    private final WebClient webClient;

    @Cacheable(value = "photos")
    public List<Photo> getPhotosByAlbumId(Long albumId) {
        return List.of(Objects.requireNonNull(webClient
                .get()
                .uri(String.join("", "/albums/", albumId.toString(), "/photos"))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceUnavailableException("API не отвечает")))
                .bodyToMono(Photo[].class)
                .block()));
    }
}
