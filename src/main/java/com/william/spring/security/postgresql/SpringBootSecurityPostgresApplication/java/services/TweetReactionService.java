package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.EReaction;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.TweetReaction;

import java.util.Map;

public interface TweetReactionService {
    /** Inserta o actualiza (upsert) una reacción */
    void saveOrUpdate(TweetReaction tr);

    /** Devuelve el conteo agrupado por tipo de reacción para un tweet */
    Map<EReaction, Long> countsByTweet(Long tweetId);
}