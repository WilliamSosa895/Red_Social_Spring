package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Comment;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.request.CommentRequest;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.response.CommentResponse;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.TweetRepository;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.UserRepository;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services.CommentService;


@RestController
@RequestMapping("/api/posts/{postId}/comments")
@CrossOrigin(origins="*")
public class CommentController {
    @Autowired private CommentService service;
    @Autowired private TweetRepository tweetRepo;
    @Autowired private UserRepository userRepo;

    @PostMapping
    public ResponseEntity<CommentResponse> add(
        Authentication auth,
        @PathVariable Long postId,
        @RequestBody CommentRequest req
    ) {
        Comment c = new Comment();
        c.setText(req.getText());
        c.setTweet(tweetRepo.findById(postId)
               .orElseThrow(() -> new RuntimeException("Post not found")));
        c.setUser(userRepo.findByUsername(auth.getName())
               .orElseThrow(() -> new RuntimeException("User not found")));

        Comment saved = service.add(c);
        CommentResponse dto = toDto(saved);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<CommentResponse>> list(
        @PathVariable Long postId,
        Pageable pg
    ) {
        Page<Comment> page = service.list(postId, pg);
        Page<CommentResponse> dtoPage = page.map(this::toDto);
        return ResponseEntity.ok(dtoPage);
    }

    private CommentResponse toDto(Comment c) {
        return new CommentResponse(
            c.getId(),
            c.getText(),
            c.getCreatedAt(),
            c.getUser().getUsername(),
            c.getTweet().getId()
        );
    }
}
