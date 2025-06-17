package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.controllers;

import java.nio.file.Paths;

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
@CrossOrigin(origins="*")
public class TweetController {
    @Autowired private TweetService service;
    @Autowired private ImageStorageService storage;
    @Autowired private LegendRepository legendRepo;
    @Autowired private UserRepository userRepo;

    @PostMapping(consumes= {"multipart/form-data"})
    public ResponseEntity<TweetResponse> create(
      Authentication auth,
      @RequestPart("tweet") TweetRequest req,
      @RequestPart(value="image", required=false) MultipartFile img
    ) {
      Tweet t = new Tweet();
      t.setContent(req.getContent());
      if (img!=null && !img.isEmpty()) {
        t.setImageUrl(storage.storeFile(img));
      }
      if (req.getLegendId()!=null) {
        Legend l = legendRepo.findById(req.getLegendId())
                             .orElseThrow(() -> new RuntimeException("Legend not found"));
        t.setLegend(l);
      }
      User u = userRepo.findByUsername(
                  ((UserDetailsImpl)auth.getPrincipal()).getUsername()
                ).orElseThrow();
      t.setPostedBy(u);

      return ResponseEntity.ok(toDto(service.create(t)));
    }

    @GetMapping
    public ResponseEntity<Page<TweetResponse>> feed(Pageable pg,
        @RequestParam(value="legendId", required=false) Long lid
    ) {
      Page<Tweet> tweets = service.feed(pg, lid);
      Page<TweetResponse> dtoPage = tweets.map(this::toDto);
      return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TweetResponse> getOne(@PathVariable Long id) {
      return ResponseEntity.ok(toDto(service.getById(id)));
    }

    private TweetResponse toDto(Tweet t) {
      // 1) Extrae sólo el filename (no toda la ruta que te devuelve storeFile)
      String stored = t.getImageUrl(); // quizás algo como "uploads/abc123.png"
      String filename = null;
      if (stored != null && !stored.isBlank()) {
        // Paths.get(...).getFileName() devuelve "abc123.png"
        filename = Paths.get(stored).getFileName().toString();
      }

      // 2) Construye la URL pública correcta
      String publicUrl = (filename != null)
        ? "/uploads/" + filename
        : null;

      Long lid = (t.getLegend() != null)
                 ? t.getLegend().getId()
                 : null;

      return new TweetResponse(
        t.getId(),
        t.getContent(),
        publicUrl,          // esta ya no tendrá doble "/uploads/uploads/..."
        t.getCreatedAt(),
        t.getPostedBy().getUsername(),
        lid,
        t.getRepostCount(),
        t.getCommentCount(),
        t.getReactionCount()
      );
    }
}
