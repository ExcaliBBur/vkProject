package com.example.vkproject.service.auth;

import com.example.vkproject.model.entity.jpa.User;
import com.example.vkproject.repository.auth.UserJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DetailsService implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;

    public User createUser(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Пользователя с таким именем не существует"));
    }
}
