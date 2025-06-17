package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Repost;

public interface RepostService {
    Repost add(Repost repost);
    Page<Repost> list(Long tweetId, Pageable pageable);
}