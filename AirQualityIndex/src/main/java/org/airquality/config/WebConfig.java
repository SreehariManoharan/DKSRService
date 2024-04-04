package org.airquality.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig class is responsible for configuring the web application.
 * CORS configuration for the application
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:9500") // Allow requests from this origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
                .allowedHeaders("*"); // Allowed headers
    }
}
