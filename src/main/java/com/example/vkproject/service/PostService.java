package com.example.vkproject.service;

import com.example.vkproject.dto.post.PostRequest;
import com.example.vkproject.model.entity.Post;
import com.example.vkproject.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.getPosts();
    }

    public Post getPost(Long id) {
        return postRepository.getPost(id);
    }

    public Post createPost(PostRequest post) {
        return postRepository.createPost(post);
    }

    public Post updatePost(PostRequest post, Long id) {
        var updatedPost = postRepository.updatePost(post, id);
        if (Objects.isNull(updatedPost.getId())) {
            throw new EntityNotFoundException("Такого поста не существует");
        }
        return updatedPost;
    }

    public void deletePost(Long id) {
        postRepository.deletePost(id);
    }

    public List<Post> getUserPosts(Long userId) {
        return postRepository.getUserPosts(userId);
    }
}
