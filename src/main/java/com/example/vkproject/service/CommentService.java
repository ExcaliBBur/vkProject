package com.example.vkproject.service;

import com.example.vkproject.model.Comment;
import com.example.vkproject.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getComments(Long id) {
        return commentRepository.getComments(id);
    }

}
