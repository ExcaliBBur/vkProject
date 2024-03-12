package com.example.vkproject.contoller.api.user;

import com.example.vkproject.dto.user.UserRequest;
import com.example.vkproject.model.entity.User;
import com.example.vkproject.service.api.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "users", description = "Контроллер для работы с пользователями")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить пользователей")
    @PreAuthorize("hasAnyAuthority('ROLE_USERS_VIEWER', 'ROLE_ADMIN_VIEWER')")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить пользователя")
    @PreAuthorize("hasAnyAuthority('ROLE_USERS_VIEWER', 'ROLE_ADMIN_VIEWER')")
    public User getUser(
            @PathVariable
            Long id
    ) {
        return userService.getUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать пользователя")
    @PreAuthorize("hasAnyAuthority('ROLE_USERS_EDITOR', 'ROLE_ADMIN_VIEWER')")
    public User createUser(
            @RequestBody
            UserRequest user
    ) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить пользователя")
    @PreAuthorize("hasAnyAuthority('ROLE_USERS_EDITOR', 'ROLE_ADMIN_VIEWER')")
    public User updateUser(
            @RequestBody
            UserRequest user,
            @PathVariable
            Long id
    ) {
        return userService.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить пользователя")
    @PreAuthorize("hasAnyAuthority('ROLE_USERS_EDITOR', 'ROLE_ADMIN_VIEWER')")
    public void deleteUser(
            @PathVariable
            Long id
    ) {
        userService.deleteUser(id);
    }

}
