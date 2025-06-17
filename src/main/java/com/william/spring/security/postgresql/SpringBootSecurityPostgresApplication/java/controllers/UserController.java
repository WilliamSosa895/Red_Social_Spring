package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Tweet;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.response.TweetResponse;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services.TweetService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="*")
public class UserController {

  @Autowired private TweetService tweetService;

  @GetMapping("/{username}/tweets")
  public ResponseEntity<Page<TweetResponse>> getUserTweets(
      @PathVariable String username,
      Pageable pageable
  ) {
    Page<Tweet> tweets = tweetService.findByUsername(username, pageable);
    Page<TweetResponse> dtoPage = tweets.map(t -> {
      Long lid = t.getLegend()!=null ? t.getLegend().getId() : null;
      return new TweetResponse(
        t.getId(), t.getContent(), t.getImageUrl(), t.getCreatedAt(),
        t.getPostedBy().getUsername(), lid,
        t.getRepostCount(), t.getCommentCount(), t.getReactionCount()
      );
    });
    return ResponseEntity.ok(dtoPage);
  }
}
