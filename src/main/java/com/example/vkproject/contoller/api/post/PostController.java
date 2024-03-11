package com.example.vkproject.contoller.api;

import com.example.vkproject.dto.post.PostRequest;
import com.example.vkproject.model.entity.Post;
import com.example.vkproject.service.api.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@Tag(name = "posts", description = "Контроллер для работы с постами")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить посты")
    public List<Post> getPosts(
            Long userId
    ) {
        if (Objects.isNull(userId)) {
            return postService.getPosts();
        }
        return postService.getUserPosts(userId);
    }

    @GetMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить пост")
    public Post getPost(
            @PathVariable
            Long id
    ) {
        return postService.getPost(id);
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать пост")
    public Post createPost(
            @RequestBody
            PostRequest post
            ) {
        return postService.createPost(post);
    }

    @PutMapping("/posts/{id}")
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

    @DeleteMapping("/posts/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить пост")
    public void deletePost(
            @PathVariable
            Long id
    ) {
        postService.deletePost(id);
    }

    @GetMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить посты пользователя")
    public List<Post> getUserPostsByPath(
            @PathVariable
            Long id
    ) {
        return postService.getUserPosts(id);
    }
}
