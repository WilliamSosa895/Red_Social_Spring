package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Repost;



public interface RepostRepository extends JpaRepository<Repost, Long> {
    Page<Repost> findByTweetIdOrderByCreatedAtDesc(Long tweetId, Pageable pageable);
}