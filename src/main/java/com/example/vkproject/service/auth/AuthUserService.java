package com.example.vkproject.service.auth;

import com.example.vkproject.dto.auth.ResponseUser;
import com.example.vkproject.dto.auth.UpdateUserRequest;
import com.example.vkproject.model.entity.jpa.Role;
import com.example.vkproject.model.entity.jpa.User;
import com.example.vkproject.repository.auth.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthUserService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    public List<ResponseUser> getUsers() {
        var users = userJpaRepository.findAll();

        return users.stream().map(user -> ResponseUser.builder()
                .id(user.getId())
                .username(user.getUsername())
                .authorities(user.getAuthorities())
                .build()).toList();
    }

    public ResponseUser getUser(Long id) {
        var user = userJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Такого пользователя не существует"));

        return ResponseUser.builder()
                .id(user.getId())
                .authorities(user.getAuthorities())
                .username(user.getUsername())
                .build();
    }

    public ResponseUser updateUser(Long id, UpdateUserRequest updateUserRequest) {
        var user = userJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Такого пользователя не существует"));

        if (!Objects.isNull(updateUserRequest.getUsername())) {
            user.setUsername(updateUserRequest.getUsername());
        }

        if (!Objects.isNull(updateUserRequest.getPassword())) {
            user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
        }

        if (!Objects.isNull(updateUserRequest.getRole())) {
            List<Role> list = new ArrayList<>();
            list.add(roleService.getRole(updateUserRequest.getRole()));
            user.setRoles(list);
        }

        var updatedUser = userJpaRepository.save(user);

        return ResponseUser.builder()
                .username(updatedUser.getUsername())
                .authorities(updatedUser.getAuthorities())
                .id(updatedUser.getId())
                .build();
    }
}
