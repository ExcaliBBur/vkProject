package com.example.vkproject.repository.api.interfaces;

import com.example.vkproject.dto.album.AlbumRequest;
import com.example.vkproject.model.entity.Album;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository {
    List<Album> getAlbums();

    Album getAlbum(Long id);

    Album createAlbum(AlbumRequest album);

    Album updateAlbum(AlbumRequest album, Long id);

    void deleteAlbum(Long id);

    List<Album> getUserAlbums(Long userId);
}
