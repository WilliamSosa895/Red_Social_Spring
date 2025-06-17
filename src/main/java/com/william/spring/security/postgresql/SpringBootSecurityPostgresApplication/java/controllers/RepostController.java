package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Repost;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.response.RepostResponse;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.*;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services.RepostService;

@RestController
@RequestMapping("/api/posts/{postId}/reposts")
@CrossOrigin(origins="*")
public class RepostController {
    @Autowired private RepostService service;
    @Autowired private TweetRepository tweetRepo;
    @Autowired private UserRepository userRepo;

    @PostMapping
    public ResponseEntity<RepostResponse> add(
        Authentication auth,
        @PathVariable Long postId
    ) {
        Repost r = new Repost();
        r.setTweet(tweetRepo.findById(postId)
               .orElseThrow(() -> new RuntimeException("Post not found")));
        r.setUser(userRepo.findByUsername(auth.getName())
               .orElseThrow(() -> new RuntimeException("User not found")));

        Repost saved = service.add(r);
        RepostResponse dto = new RepostResponse(
            saved.getId(),
            saved.getCreatedAt(),
            saved.getUser().getUsername(),
            saved.getTweet().getId()
        );
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<RepostResponse>> list(
        @PathVariable Long postId,
        Pageable pg
    ) {
        Page<Repost> page = service.list(postId, pg);
        Page<RepostResponse> dtoPage = page.map(r ->
            new RepostResponse(
                r.getId(),
                r.getCreatedAt(),
                r.getUser().getUsername(),
                r.getTweet().getId()
            )
        );
        return ResponseEntity.ok(dtoPage);
    }
}
