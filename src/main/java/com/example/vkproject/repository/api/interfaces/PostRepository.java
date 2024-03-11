package com.example.vkproject.repository.api.interfaces;

import com.example.vkproject.dto.post.PostRequest;
import com.example.vkproject.model.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository {
    List<Post> getPosts();

    Post getPost(Long id);

    Post createPost(PostRequest post);

    Post updatePost(PostRequest post, Long id);

    void deletePost(Long id);

    List<Post> getUserPosts(Long userId);
}
