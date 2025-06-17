package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);        // ← añadido
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
