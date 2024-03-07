package com.example.vkproject.contoller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/albums")
@Tag(name = "albums", description = "Контроллер для работы с альбомами")
public class AlbumController {
}
