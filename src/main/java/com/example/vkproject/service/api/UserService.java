package com.example.vkproject.service.api;

import com.example.vkproject.dto.user.UserRequest;
import com.example.vkproject.model.entity.User;
import com.example.vkproject.repository.api.implementations.UserRepositoryImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepositoryImpl userRepository;

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUser(Long id) {
        return userRepository.getUser(id);
    }

    public User createUser(UserRequest user) {
        return userRepository.createUser(user);
    }

    public User updateUser(UserRequest user, Long id) {
        var updatedUser = userRepository.updateUser(user, id);
        if (Objects.isNull(updatedUser.getId())) {
            throw new EntityNotFoundException("Такого пользователя не существует");
        }
        return updatedUser;
    }

    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }
}
