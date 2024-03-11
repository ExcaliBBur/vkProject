package com.example.vkproject.repository.auth;

import com.example.vkproject.model.entity.jpa.RefreshToken;
import com.example.vkproject.model.entity.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUser(User user);

    void deleteByToken(String token);

    boolean existsByToken(String token);
}
