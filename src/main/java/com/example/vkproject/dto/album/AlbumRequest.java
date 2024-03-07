package com.example.vkproject.dto.album;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlbumRequest {
    Long userId;

    String title;
}
