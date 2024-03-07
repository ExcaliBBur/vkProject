package com.example.vkproject.contoller;

import com.example.vkproject.dto.PostRequest;
import com.example.vkproject.model.Post;
import com.example.vkproject.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "posts", description = "Контроллер для работы с постами")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить посты")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить пост")
    public Post getPost(
            @PathVariable
            Long id
    ) {
        return postService.getPost(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать пост")
    public Post createPost(
            @RequestBody
            PostRequest post
            ) {
        return postService.createPost(post);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить пост")
    public Post updatePost(
            @RequestBody
            PostRequest post,
            @PathVariable
            Long id
    ) {
        return postService.updatePost(post, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить пост")
    public void deletePost(
            @PathVariable
            Long id
    ) {
        postService.deletePost(id);
    }

}
