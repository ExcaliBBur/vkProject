package com.example.vkproject.contoller;

import com.example.vkproject.dto.album.AlbumRequest;
import com.example.vkproject.model.entity.Album;
import com.example.vkproject.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@Tag(name = "albums", description = "Контроллер для работы с альбомами")
@AllArgsConstructor
public class AlbumController {
    private final AlbumService albumService;
    @GetMapping("/albums")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить альбомы")
    public List<Album> getAlbums(
            Long userId
    ) {
        if (Objects.isNull(userId)) {
            return albumService.getAlbums();
        }
        return albumService.getUserAlbums(userId);
    }

    @GetMapping("/albums/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить альбом")
    public Album getAlbum(
            @PathVariable
            Long id
    ) {
        return albumService.getAlbum(id);
    }

    @PostMapping("/albums")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать альбом")
    public Album createAlbum(
            @RequestBody
            AlbumRequest album
    ) {
        return albumService.createAlbum(album);
    }

    @PutMapping("/albums/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить альбом")
    public Album updateAlbum(
            @RequestBody
            AlbumRequest album,
            @PathVariable
            Long id
    ) {
        return albumService.updateAlbum(album, id);
    }

    @DeleteMapping("/albums/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить альбом")
    public void deleteAlbum(
            @PathVariable
            Long id
    ) {
       albumService.deleteAlbum(id);
    }

    @GetMapping("/users/{id}/albums")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить альбомы пользователя")
    public List<Album> getUserAlbumsByPath(
            @PathVariable
            Long id
    ) {
        return albumService.getUserAlbums(id);
    }
}
