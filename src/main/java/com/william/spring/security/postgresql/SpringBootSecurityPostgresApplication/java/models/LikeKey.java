package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class LikeKey implements Serializable {
  @Column(name = "reaction_id")
  private Long reactionId;
  @Column(name = "user_id")
  private Long userId;
  @Column(name = "tweet_id")
  private Long tweetId;

  public LikeKey() {}
  public Long getReactionId()                     { return reactionId; }
  public void setReactionId(Long reactionId)      { this.reactionId = reactionId; }
  public Long getUserId()                         { return userId; }
  public void setUserId(Long userId)              { this.userId = userId; }
  public Long getTweetId()                        { return tweetId; }
  public void setTweetId(Long tweetId)            { this.tweetId = tweetId; }
}