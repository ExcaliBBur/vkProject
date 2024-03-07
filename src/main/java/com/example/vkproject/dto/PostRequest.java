package com.example.vkproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    Long userId;

    String title;

    String body;
}
