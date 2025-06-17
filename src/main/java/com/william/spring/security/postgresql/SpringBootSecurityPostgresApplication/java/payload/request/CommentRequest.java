package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentRequest {
    @NotBlank @Size(max=140)
    private String text;

    // Getters & Setters...
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}