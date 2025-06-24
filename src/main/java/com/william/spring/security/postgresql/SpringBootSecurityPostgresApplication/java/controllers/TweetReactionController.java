package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.controllers;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.*;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.request.ReactionRequest;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.*;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services.TweetReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/posts/{postId}/reactions")
@CrossOrigin(origins = "*")
public class TweetReactionController {

    @Autowired private TweetReactionService service;
    @Autowired private UserRepository        userRepo;
    @Autowired private TweetRepository       tweetRepo;
    @Autowired private ReactionRepository    reactionRepo;

    /** Guarda o actualiza la reacción y devuelve el mapa de conteos actualizado */
    @PostMapping
    public ResponseEntity<Map<EReaction, Long>> create(
        Authentication auth,
        @PathVariable Long postId,
        @RequestBody ReactionRequest req
    ) {
        User user = userRepo.findByUsername(auth.getName())
                            .orElseThrow(() -> new RuntimeException("User not found"));
        Tweet tweet = tweetRepo.findById(postId)
                            .orElseThrow(() -> new RuntimeException("Post not found"));
        Reaction reaction = reactionRepo.findById(req.getReactionId())
                            .orElseThrow(() -> new RuntimeException("Reaction not found"));

        TweetReactionKey key = new TweetReactionKey();
        key.setUserId(user.getId());
        key.setTweetId(tweet.getId());

        TweetReaction tr = new TweetReaction();
        tr.setId(key);
        tr.setUser(user);
        tr.setTweet(tweet);
        tr.setReaction(reaction);

        service.saveOrUpdate(tr);

        Map<EReaction, Long> counts = service.countsByTweet(tweet.getId());
        return ResponseEntity.status(201).body(counts);
    }

    /** Devuelve solo el mapa de conteos por tipo */
    @GetMapping("/counts")
    public ResponseEntity<Map<EReaction, Long>> counts(
        @PathVariable Long postId
    ) {
        return ResponseEntity.ok(service.countsByTweet(postId));
    }
}
