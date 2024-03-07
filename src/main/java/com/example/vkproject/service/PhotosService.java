package com.example.vkproject.service;

import com.example.vkproject.model.entity.Photo;
import com.example.vkproject.repository.PhotosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PhotosService {
    private final PhotosRepository photosRepository;

    public List<Photo> getPhotosByAlbumId(Long albumId) {
        return photosRepository.getPhotosByAlbumId(albumId);
    }
}
