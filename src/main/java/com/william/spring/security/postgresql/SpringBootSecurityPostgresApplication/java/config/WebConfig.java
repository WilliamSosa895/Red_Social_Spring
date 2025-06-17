// File: src/main/java/com/william/spring/security/postgresql/SpringBootSecurityPostgresApplication/java/config/WebConfig.java
package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Cualquier GET /uploads/** servirá desde la carpeta local "./uploads/"
        registry
          .addResourceHandler("/uploads/**")
          .addResourceLocations("file:uploads/");
    }
}