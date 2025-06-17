package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    Page<Tweet> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Tweet> findByLegendIdOrderByCreatedAtDesc(Long legendId, Pageable pageable);
    Page<Tweet> findByPostedByUsernameOrderByCreatedAtDesc(String username, Pageable pageable);
}
