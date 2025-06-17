package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="tweets")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Tweet {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max=280)
    private String content;

    private String imageUrl;

    private Date createdAt = new Date();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="posted_by")
    private User postedBy;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="legend_id")
    private Legend legend;

    private Long repostCount = 0L;
    private Long commentCount = 0L;
    private Long reactionCount = 0L;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public User getPostedBy() { return postedBy; }
    public void setPostedBy(User postedBy) { this.postedBy = postedBy; }
    public Legend getLegend() { return legend; }
    public void setLegend(Legend legend) { this.legend = legend; }
    public Long getRepostCount() { return repostCount; }
    public void setRepostCount(Long repostCount) { this.repostCount = repostCount; }
    public Long getCommentCount() { return commentCount; }
    public void setCommentCount(Long commentCount) { this.commentCount = commentCount; }
    public Long getReactionCount() { return reactionCount; }
    public void setReactionCount(Long reactionCount) { this.reactionCount = reactionCount; }
}
