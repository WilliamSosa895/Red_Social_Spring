package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name="reposts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Repost {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Date createdAt = new Date();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="tweet_id")
    private Tweet tweet;

    // Getters & Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Tweet getTweet() { return tweet; }
    public void setTweet(Tweet tweet) { this.tweet = tweet; }
}