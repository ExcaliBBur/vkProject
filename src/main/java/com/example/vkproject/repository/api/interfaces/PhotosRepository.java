package com.example.vkproject.repository.api.interfaces;

import com.example.vkproject.model.entity.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PhotosRepository {
    List<Photo> getPhotosByAlbumId(Long albumId);
}
