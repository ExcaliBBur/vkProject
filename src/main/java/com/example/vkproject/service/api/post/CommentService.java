package com.example.vkproject.service.api.post;

import com.example.vkproject.model.entity.Comment;
import com.example.vkproject.repository.api.implementations.post.CommentRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepositoryImpl commentRepository;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.getCommentsByPostId(postId);
    }

}
