package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.response;

public class ReactionResponse {
    private Long userId;
    private Long tweetId;
    private Long reactionId;

    public ReactionResponse(Long userId, Long tweetId, Long reactionId) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.reactionId = reactionId;
    }

    // getters & setters...

    // Getters & Setters...
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getTweetId() { return tweetId; }
    public void setTweetId(Long tweetId) { this.tweetId = tweetId; }
    public Long getReactionId() { return reactionId; }
    public void setReactionId(Long reactionId) { this.reactionId = reactionId; }
}