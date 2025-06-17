package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.response;

import java.util.Date;

public class RepostResponse {
    private Long id;
    private Date createdAt;
    private String username;
    private Long tweetId;

    public RepostResponse(Long id, Date createdAt, String username, Long tweetId) {
        this.id = id;
        this.createdAt = createdAt;
        this.username = username;
        this.tweetId = tweetId;
    }

    // Getters & Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Long getTweetId() { return tweetId; }
    public void setTweetId(Long tweetId) { this.tweetId = tweetId; }
}