package com.paymentchain.customer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartupLogger implements ApplicationListener<ApplicationReadyEvent> {

    private final GlobalConfig globalConfig;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Map<String, Object> info = new HashMap<>();
        info.put("status", "UP");
        info.put("message", "Service is running and ready to accept requests.");
        info.put("activeProfile", System.getProperty("spring.profiles.active", "default"));
        info.put("globalConfig", globalConfig);
        info.put("timestamp", LocalDateTime.now());

        log.info("==== ✅ APPLICATION STARTED SUCCESSFULLY ====");
        info.forEach((k, v) -> log.info("{}: {}", k, v));
        log.info("=============================================");
    }
}
