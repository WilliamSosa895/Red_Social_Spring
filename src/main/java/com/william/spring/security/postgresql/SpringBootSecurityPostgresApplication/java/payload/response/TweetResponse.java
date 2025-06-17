package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.response;

import java.util.Date;

public class TweetResponse {
    private Long id;
    private String content;
    private String imageUrl;
    private Date createdAt;
    private String postedBy;
    private Long legendId;
    private Long repostCount;
    private Long commentCount;
    private Long reactionCount;

    public TweetResponse(Long id, String content, String imageUrl, Date createdAt,
                         String postedBy, Long legendId,
                         Long repostCount, Long commentCount, Long reactionCount) {
        this.id = id;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.postedBy = postedBy;
        this.legendId = legendId;
        this.repostCount = repostCount;
        this.commentCount = commentCount;
        this.reactionCount = reactionCount;
    }

    // Getters & Setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public String getPostedBy() { return postedBy; }
    public void setPostedBy(String postedBy) { this.postedBy = postedBy; }
    public Long getLegendId() { return legendId; }
    public void setLegendId(Long legendId) { this.legendId = legendId; }
    public Long getRepostCount() { return repostCount; }
    public void setRepostCount(Long repostCount) { this.repostCount = repostCount; }
    public Long getCommentCount() { return commentCount; }
    public void setCommentCount(Long commentCount) { this.commentCount = commentCount; }
    public Long getReactionCount() { return reactionCount; }
    public void setReactionCount(Long reactionCount) { this.reactionCount = reactionCount; }
}