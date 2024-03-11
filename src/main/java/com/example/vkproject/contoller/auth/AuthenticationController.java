package com.example.vkproject.contoller;


import com.example.vkproject.dto.auth.AuthenticateUser;
import com.example.vkproject.dto.auth.ResponseJwt;
import com.example.vkproject.model.entity.jpa.User;
import com.example.vkproject.service.auth.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "comments", description = "Контроллер для регистрации и аутентификации пользователей")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
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

}
