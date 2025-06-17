package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Legend;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services.LegendService;

@RestController
@RequestMapping("/api/legends")
@CrossOrigin(origins="*")
public class LegendController {
    @Autowired private LegendService service;

    @PostMapping
    public ResponseEntity<Legend> create(@RequestBody Legend legend) {
        return ResponseEntity.ok(service.create(legend));
    }
    @GetMapping
    public ResponseEntity<List<Legend>> list() {
        return ResponseEntity.ok(service.listAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Legend> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Legend> update(@PathVariable Long id, @RequestBody Legend legend) {
        return ResponseEntity.ok(service.update(id, legend));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}