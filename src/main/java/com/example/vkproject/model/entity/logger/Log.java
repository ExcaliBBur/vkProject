package com.example.vkproject.model.entity.logger;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@ToString
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time")
    private LocalDateTime localDateTime;

    private String username;

    private String method;

    private String uri;

    private String authorities;

    private Boolean hasPermission;

    private String headers;
}
