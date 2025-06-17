package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.controllers;


/* 
@RestController
@RequestMapping("/api/posts/{postId}/reactions")
@CrossOrigin(origins="*")
public class ReactionController {

    @Autowired private TweetReactionService service;
    @Autowired private TweetRepository      tweetRepo;
    @Autowired private UserRepository       userRepo;
    @Autowired private ReactionRepository   reactionRepo;

    @PostMapping
    public ResponseEntity<Map<Long, Long>> create(
        Authentication auth,
        @PathVariable("postId") Long postId,
        @RequestBody ReactionRequest req
    ) {
        // ———— guardamos la reacción ————
        User user = userRepo.findByUsername(auth.getName())
                     .orElseThrow(() -> new RuntimeException("User not found"));
        Tweet tweet = tweetRepo.findById(postId)
                      .orElseThrow(() -> new RuntimeException("Post not found"));
        Reaction reaction = reactionRepo.findById(req.getReactionId())
                              .orElseThrow(() -> new RuntimeException("Reaction not found"));

        TweetReactionKey key = new TweetReactionKey();
        key.setUserId(user.getId());
        key.setTweetId(tweet.getId());
        key.setReactionId(reaction.getId());

        TweetReaction tr = new TweetReaction();
        tr.setId(key);
        tr.setUser(user);
        tr.setTweet(tweet);
        tr.setReaction(reaction);

        service.add(tr);

        // ———— devolvemos el conteo actualizado ————
        Map<Long, Long> counts = service.countsByTweet(postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(counts);
    }

    @GetMapping("/counts")
    public ResponseEntity<Map<Long, Long>> counts(@PathVariable Long postId) {
        Map<Long, Long> counts = service.countsByTweet(postId);
        return ResponseEntity.ok(counts);
    }
}
*/