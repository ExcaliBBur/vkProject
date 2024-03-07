package com.example.vkproject.contoller;

import com.example.vkproject.model.entity.Comment;
import com.example.vkproject.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
@Tag(name = "comments", description = "Контроллер для работы с комментариями")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/posts/{id}/comments")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить комментарии у поста")
    public List<Comment> getCommentsByPath(
            @PathVariable
            Long id
    ) {
        return commentService.getCommentsByPostId(id);
    }

    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить комментарии у поста")
    public List<Comment> getCommentsByRequest(
            Long postId
    ) {
        return commentService.getCommentsByPostId(postId);
    }
}
