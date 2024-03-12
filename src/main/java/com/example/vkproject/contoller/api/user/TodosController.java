package com.example.vkproject.contoller.api.user;

import com.example.vkproject.model.entity.Todos;
import com.example.vkproject.service.api.user.TodosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "todos", description = "Контроллер для работы с тудушками")
@AllArgsConstructor
public class TodosController {
    private final TodosService todosService;

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить тудушки пользователя")
    @PreAuthorize("hasAnyAuthority('ROLE_USERS_VIEWER', 'ROLE_ADMIN_VIEWER')")
    public List<Todos> getTodosByRequest(
            Long userId
    ) {
        return todosService.getUserTodos(userId);
    }

    @GetMapping("/users/{id}/todos")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить тудушки пользователя")
    @PreAuthorize("hasAnyAuthority('ROLE_USERS_VIEWER', 'ROLE_ADMIN_VIEWER')")
    public List<Todos> getTodosByPath(
            @PathVariable
            Long id
    ) {
        return todosService.getUserTodos(id);
    }
}
