package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Comment;

public interface CommentService {
    Comment add(Comment comment);
    Page<Comment> list(Long tweetId, Pageable pageable);
}