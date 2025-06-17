package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.User;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private UserRepository userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Primero por username...
        Optional<User> opt = userRepo.findByUsername(usernameOrEmail);
        // ...si no existe, por email
        User user = opt.orElseGet(() ->
            userRepo.findByEmail(usernameOrEmail)
                    .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail)
                    )
        );
        return UserDetailsImpl.build(user);
    }
}
