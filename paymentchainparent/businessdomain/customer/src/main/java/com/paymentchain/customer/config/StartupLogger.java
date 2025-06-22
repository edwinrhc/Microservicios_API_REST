package com.paymentchain.customer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.core.env.Environment;
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
    private final Environment environment;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Map<String, Object> info = new HashMap<>();
        info.put("status", "UP");
        info.put("message", "Service is running and ready to accept requests.");
        String[] activeProfiles = environment.getActiveProfiles();
        info.put("activeProfile", activeProfiles.length > 0 ? String.join(",", activeProfiles) : "default");
        info.put("globalConfig", globalConfig);
        info.put("timestamp", LocalDateTime.now());

        // Obtener el puerto
        String port = environment.getProperty("local.server.port");
        info.put("port", port != null ? port : "unknown");

        log.info("==== ✅ APPLICATION STARTED SUCCESSFULLY ====");
        info.forEach((k, v) -> log.info("{}: {}", k, v));
        log.info("=============================================");
    }
}
