package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {}
