package com.example.vkproject.service;

import com.example.vkproject.dto.album.AlbumRequest;
import com.example.vkproject.model.entity.Album;
import com.example.vkproject.repository.api.interfaces.AlbumRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    public List<Album> getAlbums() {
        return albumRepository.getAlbums();
    }

    public Album getAlbum(Long id) {
        return albumRepository.getAlbum(id);
    }

    public Album createAlbum(AlbumRequest album) {
        return albumRepository.createAlbum(album);
    }

    public Album updateAlbum(AlbumRequest album, Long id) {
        var updatedAlbum = albumRepository.updateAlbum(album, id);
        if (Objects.isNull(updatedAlbum.getId())) {
            throw new EntityNotFoundException("Такого альбома не существует");
        }
        return updatedAlbum;
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteAlbum(id);
    }

    public List<Album> getUserAlbums(Long userId) {
        return albumRepository.getUserAlbums(userId);
    }
}
