package com.example.vkproject.repository.api.interfaces;

import com.example.vkproject.model.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository {
    List<Comment> getCommentsByPostId(Long postId);
}
