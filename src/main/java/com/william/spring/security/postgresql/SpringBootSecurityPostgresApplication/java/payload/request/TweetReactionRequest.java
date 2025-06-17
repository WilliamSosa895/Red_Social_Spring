package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.request;

import jakarta.validation.constraints.NotNull;

public class TweetReactionRequest {
  @NotNull private Long tweetId;
  @NotNull private Long reactionId;

  public TweetReactionRequest() {}
  public Long getTweetId()              { return tweetId; }
  public void setTweetId(Long t)        { this.tweetId = t; }
  public Long getReactionId()           { return reactionId; }
  public void setReactionId(Long r)     { this.reactionId = r; }
}
