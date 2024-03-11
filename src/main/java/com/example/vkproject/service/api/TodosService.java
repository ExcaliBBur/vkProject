package com.example.vkproject.service;

import com.example.vkproject.model.entity.Todos;
import com.example.vkproject.repository.api.implementations.TodosRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodosService {
    private final TodosRepositoryImpl todosRepository;

    public List<Todos> getUserTodos(Long userId) {
        return todosRepository.getUserTodos(userId);
    }
}
