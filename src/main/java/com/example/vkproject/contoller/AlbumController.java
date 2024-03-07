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

@RestController
@RequestMapping("/api/albums")
@Tag(name = "albums", description = "Контроллер для работы с альбомами")
@AllArgsConstructor
public class AlbumController {
    private final AlbumService albumService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить альбомы")
    public List<Album> getAlbums() {
        return albumService.getAlbums();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить альбом")
    public Album getAlbum(
            @PathVariable
            Long id
    ) {
        return albumService.getAlbum(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать альбом")
    public Album createAlbum(
            @RequestBody
            AlbumRequest album
    ) {
        return albumService.createAlbum(album);
    }

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить альбом")
    public void deleteAlbum(
            @PathVariable
            Long id
    ) {
       albumService.deleteAlbum(id);
    }
}
