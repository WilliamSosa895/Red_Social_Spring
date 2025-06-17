package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Legend;

public interface LegendRepository extends JpaRepository<Legend, Long> {}