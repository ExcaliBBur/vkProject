package com.example.vkproject.repository.api.interfaces;

import com.example.vkproject.dto.user.UserRequest;
import com.example.vkproject.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository {
    List<User> getUsers();

    User getUser(Long id);

    User createUser(UserRequest user);

    User updateUser(UserRequest user, Long id);

    void deleteUser(Long id);

}
