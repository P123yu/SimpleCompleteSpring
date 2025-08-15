package com.simple.Simple.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringAudit {

    @Bean
    public AuditorAware<String> auditorAware() {
        // Replace with logic to get current user from Spring Security if needed
        return () -> Optional.of("system");
    }

}
