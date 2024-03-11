package com.example.vkproject.contoller.api;

import com.example.vkproject.dto.user.UserRequest;
import com.example.vkproject.model.entity.User;
import com.example.vkproject.service.api.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить пользователя")
    public User getUser(
            @PathVariable
            Long id
    ) {
        return userService.getUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Создать пользователя")
    public User createUser(
            @RequestBody
            UserRequest user
    ) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить пользователя")
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
    public void deleteUser(
            @PathVariable
            Long id
    ) {
        userService.deleteUser(id);
    }

}
