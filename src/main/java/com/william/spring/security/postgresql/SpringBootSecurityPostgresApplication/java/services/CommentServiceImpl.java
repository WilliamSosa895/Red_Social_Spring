
package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Comment;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.CommentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired private CommentRepository repo;
    @Override public Comment add(Comment c) { return repo.save(c); }
    @Override public Page<Comment> list(Long tweetId, Pageable p) {
        return repo.findByTweetIdOrderByCreatedAtAsc(tweetId, p);
    }
}