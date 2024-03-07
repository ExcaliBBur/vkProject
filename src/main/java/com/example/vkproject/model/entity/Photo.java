package com.example.vkproject.model.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Photo {
    Long albumId;

    Long id;

    String title;

    String url;

    String thumbnailUrl;
}
