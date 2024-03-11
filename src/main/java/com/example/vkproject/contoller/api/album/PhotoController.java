package com.example.vkproject.contoller.api.album;

import com.example.vkproject.model.entity.Photo;
import com.example.vkproject.service.api.PhotosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(name = "photos", description = "Контроллер для работы с фотографиями")
@AllArgsConstructor
public class PhotoController {
    private final PhotosService photosService;
    @GetMapping("/albums/{id}/photos")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить фотографии в альбоме ")
    @PreAuthorize("hasAnyAuthority('ROLE_ALBUMS_VIEWER', 'ROLE_ADMIN_VIEWER')")
    public List<Photo> getPhotosByPath(
            @PathVariable
            Long id
    ) {
        return photosService.getPhotosByAlbumId(id);
    }

    @GetMapping("/photos")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить фотографии в альбоме")
    @PreAuthorize("hasAnyAuthority('ROLE_ALBUMS_VIEWER', 'ROLE_ADMIN_VIEWER')")
    public List<Photo> getPhotosByRequest(
            Long albumId
    ) {
        return photosService.getPhotosByAlbumId(albumId);
    }
}
