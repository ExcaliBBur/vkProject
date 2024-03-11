package com.example.vkproject.service.api;

import com.example.vkproject.model.entity.Photo;
import com.example.vkproject.repository.api.implementations.PhotosRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhotosService {
    private final PhotosRepositoryImpl photosRepository;

    public List<Photo> getPhotosByAlbumId(Long albumId) {
        return photosRepository.getPhotosByAlbumId(albumId);
    }
}
