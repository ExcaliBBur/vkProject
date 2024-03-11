package com.example.vkproject.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthenticateUser {

    private String username;

    @Size(min = 8, max = 16, message = "Пароль должен быть от 8 до 16 символов")
    @NotBlank(message = "Пароль не должен быть пустым")
    private String password;

}
