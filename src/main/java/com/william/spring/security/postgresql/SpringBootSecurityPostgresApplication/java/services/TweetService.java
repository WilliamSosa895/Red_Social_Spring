package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Tweet;

public interface TweetService {
    Tweet create(Tweet tweet);
    Page<Tweet> feed(Pageable pageable, Long legendId);
    Tweet getById(Long id);
    Page<Tweet> findByUsername(String username, Pageable pageable);
}
