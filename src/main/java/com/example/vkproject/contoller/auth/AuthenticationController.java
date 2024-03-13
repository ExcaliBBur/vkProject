package com.example.vkproject.contoller.auth;


import com.example.vkproject.dto.auth.AuthenticateUser;
import com.example.vkproject.dto.auth.RefreshRequest;
import com.example.vkproject.dto.auth.ResponseJwt;
import com.example.vkproject.model.entity.jpa.User;
import com.example.vkproject.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "auth", description = "Контроллер для регистрации и аутентификации пользователей")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Зарегистрироваться")
    public ResponseJwt register(
            @Valid
            @RequestBody
            AuthenticateUser details
    ) {
        return authenticationService.register(User.builder()
                .username(details.getUsername())
                .password(details.getPassword())
                .build());
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Аутентификация")
    public ResponseJwt authenticate(
            @Valid
            @RequestBody
            AuthenticateUser details
    ) {
        return authenticationService.authenticate(User.builder()
                .username(details.getUsername())
                .password(details.getPassword())
                .build());
    }

    @PostMapping("/access")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получить текущий jwt токен")
    public ResponseJwt access(
            @RequestBody
            RefreshRequest refresh
    ) {
        return authenticationService.getAccessToken(refresh.getRefresh());
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновить jwt токен по refresh токену")
    public ResponseJwt refresh(
            @RequestBody
            RefreshRequest refresh
    ) {
        return authenticationService.refreshToken(refresh.getRefresh());
    }

    @DeleteMapping("/erase")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Удалить refresh токен")
    public boolean eraseRefresh(
            @RequestBody
            RefreshRequest refresh
    ) {
        authenticationService.eraseRefreshToken(refresh.getRefresh());

        return true;
    }
}
