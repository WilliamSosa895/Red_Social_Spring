package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.EReaction;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.ERole;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Reaction;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Role;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.ReactionRepository;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.RoleRepository;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepo;
    private final ReactionRepository reactionRepo;

    public DataInitializer(RoleRepository roleRepo, ReactionRepository reactionRepo) {
        this.roleRepo = roleRepo;
        this.reactionRepo = reactionRepo;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Sembrar roles
        Arrays.stream(ERole.values()).forEach(er -> {
            if (!roleRepo.findByName(er).isPresent()) {
                roleRepo.save(new Role(er));
            }
        });

        // Sembrar reacciones
        Arrays.stream(EReaction.values()).forEach(er -> {
            boolean exists = reactionRepo.findAll()
                                         .stream()
                                         .anyMatch(r -> r.getType() == er);
            if (!exists) {
                reactionRepo.save(new Reaction(er));
            }
        });
    }
}
