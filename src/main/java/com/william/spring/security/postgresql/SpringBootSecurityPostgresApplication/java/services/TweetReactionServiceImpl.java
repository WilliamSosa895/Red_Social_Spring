package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.EReaction;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.TweetReaction;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.TweetReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class TweetReactionServiceImpl implements TweetReactionService {

    @Autowired
    private TweetReactionRepository repo;

    @Override
    public void saveOrUpdate(TweetReaction tr) {
        repo.save(tr);
    }

    @Override
    public Map<EReaction, Long> countsByTweet(Long tweetId) {
        List<Object[]> rows = repo.countByTweetGrouped(tweetId);
        return rows.stream().collect(Collectors.toMap(
            row -> (EReaction) row[0],
            row -> ((Number) row[1]).longValue()
        ));
    }
}
