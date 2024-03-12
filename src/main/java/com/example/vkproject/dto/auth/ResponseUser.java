package com.example.vkproject.dto.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Setter
@Getter
@Builder
public class ResponseUser {

    private Long id;

    private String username;

    private Collection<? extends GrantedAuthority> authorities;
}
