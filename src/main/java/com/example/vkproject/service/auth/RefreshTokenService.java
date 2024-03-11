package com.example.vkproject.service.auth;


import com.example.vkproject.model.entity.jpa.RefreshToken;
import com.example.vkproject.model.entity.jpa.User;
import com.example.vkproject.repository.auth.RefreshTokenRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void setToken(User user, String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user)
                .orElse(new RefreshToken(null, user, null));
        refreshToken.setToken(token);

        refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken getToken(User user) {
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("There is no token for this user"));

        return refreshToken;
    }

    @Transactional
    public void deleteToken(String refresh) {
        if (!refreshTokenRepository.existsByToken(refresh)) {
            throw new EntityNotFoundException("There is no such token");
        }

        refreshTokenRepository.deleteByToken(refresh);

    }

}
