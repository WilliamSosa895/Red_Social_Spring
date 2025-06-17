package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.request;

public class ResetPasswordRequest {
    private String email;
    private String token;
    private String password;

    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}