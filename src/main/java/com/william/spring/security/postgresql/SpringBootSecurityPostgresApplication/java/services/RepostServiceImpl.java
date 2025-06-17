package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Repost;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.RepostRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RepostServiceImpl implements RepostService {
    @Autowired private RepostRepository repo;
    @Override public Repost add(Repost r) { return repo.save(r); }
    @Override public Page<Repost> list(Long tweetId, Pageable p) {
        return repo.findByTweetIdOrderByCreatedAtDesc(tweetId, p);
    }
}