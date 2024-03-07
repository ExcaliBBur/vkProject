package com.example.vkproject.contoller;

import com.example.vkproject.model.entity.Todos;
import com.example.vkproject.service.TodosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public List<Todos> getTodosByRequest(
            Long userId
    ) {
        return todosService.getUserTodos(userId);
    }

    @GetMapping("/users/{id}/todos")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить тудушки пользователя")
    public List<Todos> getTodosByPath(
            @PathVariable
            Long id
    ) {
        return todosService.getUserTodos(id);
    }
}
