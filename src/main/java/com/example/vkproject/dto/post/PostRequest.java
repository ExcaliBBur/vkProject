package com.example.vkproject.dto.post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequest {
    Long userId;

    String title;

    String body;
}
