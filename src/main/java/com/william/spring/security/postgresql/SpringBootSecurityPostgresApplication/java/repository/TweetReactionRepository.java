package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.TweetReaction;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.TweetReactionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetReactionRepository extends JpaRepository<TweetReaction, TweetReactionKey> {

    // Devuelve para cada tipo de reacción (EReaction) la cantidad de registros en tweet_reactions
    @Query("""
        SELECT tr.reaction.type, COUNT(tr)
        FROM TweetReaction tr
        WHERE tr.tweet.id = :tweetId
        GROUP BY tr.reaction.type
    """)
    List<Object[]> countByTweetGrouped(@Param("tweetId") Long tweetId);
}
