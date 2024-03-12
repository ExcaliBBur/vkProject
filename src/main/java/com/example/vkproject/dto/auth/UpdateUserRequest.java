package com.example.vkproject.dto.auth;

import com.example.vkproject.constraint.UserRoleConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateUserRequest {

    @Pattern(regexp = "(.*\\S.*|^$)", message = "Логин не должен состоять только из пробельных символов")
    private String username;

    @Pattern(regexp = "(.*\\S.*|^$)", message = "Пароль не должен состоять только из пробельных символов")
    private String password;

    @Schema(allowableValues = { "ROLE_ADMIN", "ROLE_POSTS", "ROLE_ALBUMS", "ROLE_USERS" })
    @UserRoleConstraint(message = "Неподходящая роль. " +
            "Доступны следующие роли: \"ROLE_ADMIN\", \"ROLE_POSTS\", \"ROLE_ALBUMS\", \"ROLE_USERS\"")
    private String role;
}
