package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.ERole;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}