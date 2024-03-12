package com.example.vkproject.repository.api.interfaces.user;

import com.example.vkproject.model.entity.Todos;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TodosRepository {
    List<Todos> getUserTodos(Long userId);
}
