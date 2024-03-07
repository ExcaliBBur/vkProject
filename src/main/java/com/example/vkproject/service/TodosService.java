package com.example.vkproject.service;

import com.example.vkproject.model.entity.Todos;
import com.example.vkproject.repository.TodosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodosService {
    private final TodosRepository todosRepository;

    public List<Todos> getUserTodos(Long userId) {
        return todosRepository.getUserTodos(userId);
    }
}
