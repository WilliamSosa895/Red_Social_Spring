package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByTweetIdOrderByCreatedAtAsc(Long tweetId, Pageable pageable);
}