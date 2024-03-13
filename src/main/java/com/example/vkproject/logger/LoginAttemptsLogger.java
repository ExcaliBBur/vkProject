package com.example.vkproject.logger;

import com.example.vkproject.model.entity.jpa.User;
import com.example.vkproject.model.entity.logger.Log;
import com.example.vkproject.service.logger.LoggerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AbstractAuditListener;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.security.authorization.event.AuthorizationGrantedEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@AllArgsConstructor
public class LoginAttemptsLogger {
    private final LoggerService loggerService;

    @EventListener
    public <T> void onFailure(AuthorizationDeniedEvent<T> failure) {
        Log log = new Log();
        try {
            User user = (User) (failure.getAuthentication().get().getPrincipal());
            log.setUsername(user.getUsername());
            log.setAuthorities(user.getAuthorities().toString());
        } catch (ClassCastException e) {
            log.setUsername("anonymous");
            log.setAuthorities("");
        }

        HttpServletRequest request = getRequest();
        log.setMethod(request.getMethod());
        log.setUri(request.getRequestURI());
        log.setLocalDateTime(LocalDateTime.now());
        log.setHeaders(" ");
        log.setHasPermission(false);

        loggerService.saveLog(log);
        loggerService.log(log);
    }

    @EventListener
    public <T> void onSuccess(AuthorizationGrantedEvent<T> granted) {
        Log log = new Log();
        try {
            User user = (User) (granted.getAuthentication().get().getPrincipal());
            log.setUsername(user.getUsername());
            log.setAuthorities(user.getAuthorities().toString());
        } catch (ClassCastException e) {
            log.setUsername("anonymous");
            log.setAuthorities("");
        }

        HttpServletRequest request = getRequest();
        log.setMethod(request.getMethod());
        log.setUri(request.getRequestURI());
        log.setLocalDateTime(LocalDateTime.now());
        log.setHeaders(" ");
        log.setHasPermission(true);

        loggerService.saveLog(log);
        loggerService.log(log);
    }

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
