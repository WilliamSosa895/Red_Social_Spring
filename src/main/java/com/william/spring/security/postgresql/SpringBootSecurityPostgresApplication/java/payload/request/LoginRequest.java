package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.request;

import jakarta.validation.constraints.NotBlank;


public class LoginRequest {
    @NotBlank private String username;
    @NotBlank private String password;

    // Getters & Setters...
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}