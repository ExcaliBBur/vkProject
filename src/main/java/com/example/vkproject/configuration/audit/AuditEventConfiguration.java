package com.example.vkproject.configuration.audit;

import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.boot.actuate.security.AuthenticationAuditListener;
import org.springframework.boot.actuate.security.AuthorizationAuditListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.SpringAuthorizationEventPublisher;

@Configuration
public class AuditEventConfiguration {
    @Bean
    public InMemoryAuditEventRepository auditEventRepository() {
        return new InMemoryAuditEventRepository();
    }
}
