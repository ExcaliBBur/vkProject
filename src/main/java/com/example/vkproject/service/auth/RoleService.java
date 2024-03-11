package com.example.vkproject.service.auth;

import com.example.vkproject.model.entity.jpa.Role;
import com.example.vkproject.repository.auth.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRole(String name) {
        Role role = roleRepository.findRoleByName(name)
                .orElseThrow(() -> new EntityNotFoundException("There is no role with such name"));

        return role;
    }

}
