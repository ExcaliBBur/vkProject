package com.example.vkproject.contoller.auth;

import com.example.vkproject.dto.auth.ResponseUser;
import com.example.vkproject.dto.auth.UpdateUserRequest;
import com.example.vkproject.service.auth.AuthUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "auth", description = "Контроллер для работы с пользователями для администратора")
@AllArgsConstructor
public class AuthUserController {
    private final AuthUserService authUserService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить всех существующих пользователей")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEWER')")
    public List<ResponseUser> getUsers() {
        return authUserService.getUsers();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить пользователя")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_VIEWER')")
    public ResponseUser getUser(
            @PathVariable
            Long id
    ) {
        return authUserService.getUser(id);
    }

    @PatchMapping ("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Изменить информацию о пользователе")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN_EDITOR')")
    public ResponseUser updateUser(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            UpdateUserRequest updateUserRequest
    ) {
        return authUserService.updateUser(id, updateUserRequest);
    }
}
