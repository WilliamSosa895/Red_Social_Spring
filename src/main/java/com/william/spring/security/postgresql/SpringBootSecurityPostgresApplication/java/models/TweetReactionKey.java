// File: src/main/java/com/william/spring/security/postgresql/SpringBootSecurityPostgresApplication/java/models/TweetReactionKey.java
package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TweetReactionKey implements Serializable {

    @Column(name="user_id")
    private Long userId;

    @Column(name="tweet_id")
    private Long tweetId;

    @Column(name="reaction_id")
    private Long reactionId;

    // Constructor sin argumentos (requerido por JPA)
    public TweetReactionKey() {}

    // Getters & Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getTweetId() { return tweetId; }
    public void setTweetId(Long tweetId) { this.tweetId = tweetId; }
    public Long getReactionId() { return reactionId; }
    public void setReactionId(Long reactionId) { this.reactionId = reactionId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TweetReactionKey)) return false;
        TweetReactionKey that = (TweetReactionKey) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(tweetId, that.tweetId) &&
               Objects.equals(reactionId, that.reactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, tweetId, reactionId);
    }
}
