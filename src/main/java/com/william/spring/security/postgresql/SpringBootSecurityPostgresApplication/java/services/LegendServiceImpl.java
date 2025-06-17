package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Legend;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.LegendRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LegendServiceImpl implements LegendService {
    private final LegendRepository repo;
    public LegendServiceImpl(LegendRepository repo) { this.repo = repo; }

    @Override public Legend create(Legend legend) { return repo.save(legend); }

    @Override public Legend update(Long id, Legend legend) {
        Legend existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Legend not found"));
        existing.setName(legend.getName());
        existing.setTeam(legend.getTeam());
        existing.setPosition(legend.getPosition());
        existing.setPhotoUrl(legend.getPhotoUrl());
        return repo.save(existing);
    }

    @Override public void delete(Long id) { repo.deleteById(id); }

    @Override public Legend getById(Long id) { return repo.findById(id).orElseThrow(() -> new RuntimeException("Legend not found")); }

    @Override public List<Legend> listAll() { return repo.findAll(); }
}