package com.example.mcp_server;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;

@SpringBootApplication
public class DateTimeServerApplication {

    @Bean
    public ToolCallbackProvider myTools() {
        return MethodToolCallbackProvider.builder().toolObjects(new TimeService()).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(DateTimeServerApplication.class, args);
    }

    class TimeService {
        @Tool(description = "Get current datetime", returnDirect = true)
        String getCurrentDateTime() {
            return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
        }
    }
}
