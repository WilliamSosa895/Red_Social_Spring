package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.controllers;

import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.*;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.request.TweetRequest;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.response.TweetResponse;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.*;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services.*;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class TweetController {

    @Autowired private TweetService service;
    @Autowired private ImageStorageService storage;
    @Autowired private UserRepository userRepo;
    @Autowired private TweetReactionService reactionService;
    @Autowired private ReactionRepository reactionRepo;

    /** Crea un nuevo tweet con opcional imagen */
    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<TweetResponse> create(
        Authentication auth,
        @RequestPart("tweet") TweetRequest req,
        @RequestPart(value = "image", required = false) MultipartFile img
    ) {
        Tweet t = new Tweet();
        t.setContent(req.getContent());
        if (img != null && !img.isEmpty()) {
            t.setImageUrl(storage.storeFile(img));
        }
        User u = userRepo.findByUsername(
            ((UserDetailsImpl) auth.getPrincipal()).getUsername()
        ).orElseThrow(() -> new RuntimeException("User not found"));
        t.setPostedBy(u);
        Tweet saved = service.create(t);
        return ResponseEntity.ok(toDto(saved));
    }

    /** Feed paginado */
    @GetMapping
    public ResponseEntity<Page<TweetResponse>> feed(Pageable pg) {
        Page<Tweet> tweets = service.feed(pg, null);
        return ResponseEntity.ok(tweets.map(this::toDto));
    }

    /** Detalle de un tweet */
    @GetMapping("/{id}")
    public ResponseEntity<TweetResponse> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(toDto(service.getById(id)));
    }

    /** Conteo de reacciones por tipo */
    @GetMapping("/{id}/reactions/counts")
    public Map<EReaction, Long> counts(@PathVariable("id") Long tweetId) {
        return reactionService.countsByTweet(tweetId);
    }

    /** Upsert de reacción y devuelve conteos actualizados */
    @PostMapping("/{id}/reactions")
    public Map<EReaction, Long> react(
        @PathVariable("id") Long tweetId,
        @RequestBody Map<String, Long> payload,
        Authentication auth
    ) {
        Long reactionId = payload.get("reactionId");
        User user = userRepo.findByUsername(
            ((UserDetailsImpl) auth.getPrincipal()).getUsername()
        ).orElseThrow(() -> new RuntimeException("User not found"));
        Tweet tweet = service.getById(tweetId);
        Reaction reaction = reactionRepo.findById(reactionId)
            .orElseThrow(() -> new RuntimeException("Reaction not found"));

        TweetReaction tr = new TweetReaction(tweet, user, reaction);
        reactionService.saveOrUpdate(tr);

        return reactionService.countsByTweet(tweetId);
    }

    /** Helper para convertir entidad a DTO */
    private TweetResponse toDto(Tweet t) {
        String stored = t.getImageUrl(); 
        String filename = null;
        if (stored != null && !stored.isBlank()) {
            filename = Paths.get(stored).getFileName().toString();
        }
        String publicUrl = (filename != null)
            ? "/uploads/" + filename
            : null;

        return new TweetResponse(
            t.getId(),
            t.getContent(),
            publicUrl,
            t.getCreatedAt(),
            t.getPostedBy().getUsername(),
            t.getLegend() != null ? t.getLegend().getId() : null,
            t.getRepostCount(),
            t.getCommentCount(),
            t.getReactionCount()
        );
    }
}
