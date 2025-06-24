package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TweetReactionKey implements Serializable {
    @Column(name = "tweet_id")
    private Long tweetId;

    @Column(name = "user_id")
    private Long userId;

    public TweetReactionKey() {}

    public TweetReactionKey(Long tweetId, Long userId) {
        this.tweetId = tweetId;
        this.userId  = userId;
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TweetReactionKey)) return false;
        TweetReactionKey that = (TweetReactionKey) o;
        return Objects.equals(tweetId, that.tweetId)
            && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweetId, userId);
    }
}
