package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "tweet_reactions")
public class TweetReaction {

    @EmbeddedId
    private TweetReactionKey id;

    @MapsId("tweetId")
    @ManyToOne
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "reaction_id", nullable = false)
    private Reaction reaction;

    public TweetReaction() {}

    public TweetReaction(Tweet tweet, User user, Reaction reaction) {
        this.id       = new TweetReactionKey(tweet.getId(), user.getId());
        this.tweet    = tweet;
        this.user     = user;
        this.reaction = reaction;
    }

    public TweetReactionKey getId() {
        return id;
    }

    public void setId(TweetReactionKey id) {
        this.id = id;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }
}
