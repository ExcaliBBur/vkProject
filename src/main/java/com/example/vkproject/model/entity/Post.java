package com.example.vkproject.model.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Post {

    Long userId;

    Long id;

    String title;

    String body;
}
