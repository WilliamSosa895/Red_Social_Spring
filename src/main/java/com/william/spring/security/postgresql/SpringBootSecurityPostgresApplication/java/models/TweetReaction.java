package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "tweet_reactions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TweetReaction {
    @EmbeddedId
    private TweetReactionKey id;

    @ManyToOne @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne @MapsId("tweetId")
    @JoinColumn(name = "tweet_id")
    private Tweet tweet;

    @ManyToOne @MapsId("reactionId")
    @JoinColumn(name = "reaction_id")
    private Reaction reaction;

    public TweetReactionKey getId() { return id; }
    public void setId(TweetReactionKey id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Tweet getTweet() { return tweet; }
    public void setTweet(Tweet tweet) { this.tweet = tweet; }
    public Reaction getReaction() { return reaction; }
    public void setReaction(Reaction reaction) { this.reaction = reaction; }
}

