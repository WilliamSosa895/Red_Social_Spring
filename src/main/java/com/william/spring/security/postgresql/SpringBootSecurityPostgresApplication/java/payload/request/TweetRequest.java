package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TweetRequest {
    @NotBlank @Size(max=280)
    private String content;
    private Long legendId;

    // Getters & Setters...
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Long getLegendId() { return legendId; }
    public void setLegendId(Long legendId) { this.legendId = legendId; }
}