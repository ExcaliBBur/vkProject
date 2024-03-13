package com.example.vkproject.service.auth;

import com.example.vkproject.dto.auth.ResponseJwt;
import com.example.vkproject.exception.InvalidJwtException;
import com.example.vkproject.model.entity.jpa.RefreshToken;
import com.example.vkproject.model.entity.jpa.Role;
import com.example.vkproject.model.entity.jpa.User;
import com.example.vkproject.utils.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final DetailsService detailsService;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    public ResponseJwt register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> list = new ArrayList<>();
        list.add(roleService.getRole("ROLE_ADMIN"));
        user.setRoles(list);

        user = detailsService.createUser(user);
        String access = generateAccessToken(user);
        String refresh = generateRefreshToken(user);

        return new ResponseJwt(access, refresh);
    }

    public ResponseJwt authenticate(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        user = (User) authentication.getPrincipal();
        String access = generateAccessToken(user);
        String refresh = generateRefreshToken(user);

        return new ResponseJwt(access, refresh);
    }

    private String generateAccessToken(User user) {
        return generateAccessToken(new HashMap<>(), user);
    }

    private String generateAccessToken(Map<String, Object> extraClaims, User user) {
        return jwtUtils.generateAccessToken(extraClaims, user);
    }

    private String generateRefreshToken(User user) {
        String token = jwtUtils.generateRefreshToken(user);
        refreshTokenService.setToken(user, token);

        return token;
    }

    public ResponseJwt getAccessToken(String refresh) {
        User user = extractUser(refresh);

        String access = generateAccessToken(user);

        return new ResponseJwt(access, refresh);
    }

    public ResponseJwt refreshToken(String refresh) {
        User user = extractUser(refresh);

        String updatedRefresh = generateRefreshToken(user);
        String updatedAccess = generateAccessToken(user);

        return new ResponseJwt(updatedAccess, updatedRefresh);
    }

    private User extractUser(String refresh) {
        if (!jwtUtils.isRefreshTokenValid(refresh)) {
            throw new InvalidJwtException("Недействительный refresh токен");
        }

        String username = jwtUtils.extractRefreshUsername(refresh);
        User user = (User) detailsService.loadUserByUsername(username);
        RefreshToken savedRefresh = refreshTokenService.getToken(user);

        if (!savedRefresh.getToken().equals(refresh)) {
            throw new InvalidJwtException("Несоответствие refresh токенов");
        }

        return user;
    }

    public void eraseRefreshToken(String refresh) {
        refreshTokenService.deleteToken(refresh);
    }
}
