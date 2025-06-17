package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Tweet;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.TweetRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TweetServiceImpl implements TweetService {
    private final TweetRepository repo;
    public TweetServiceImpl(TweetRepository repo) { this.repo = repo; }

    @Override public Tweet create(Tweet t) { return repo.save(t); }
    @Override public Page<Tweet> feed(Pageable p, Long lid) {
        return (lid != null)
          ? repo.findByLegendIdOrderByCreatedAtDesc(lid, p)
          : repo.findAllByOrderByCreatedAtDesc(p);
    }
    @Override public Tweet getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }
    @Override public Page<Tweet> findByUsername(String user, Pageable p) {
        return repo.findByPostedByUsernameOrderByCreatedAtDesc(user, p);
    }
}
