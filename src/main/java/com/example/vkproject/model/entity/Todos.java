package com.example.vkproject.model.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Todos {
    Long userId;

    Long id;

    String title;

    Boolean completed;
}
