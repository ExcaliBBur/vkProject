package com.example.vkproject.model.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Comment {
    Long postId;

    Long id;

    String name;

    String email;

    String body;
}
