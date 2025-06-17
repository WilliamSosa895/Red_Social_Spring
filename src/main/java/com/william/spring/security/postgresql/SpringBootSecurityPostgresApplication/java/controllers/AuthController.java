package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.controllers;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.*;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.request.*;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.payload.response.*;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.repository.*;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.security.jwt.JwtUtils;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private UserRepository userRepo;
    @Autowired private RoleRepository roleRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtils jwtUtils;
    @Autowired private MailService mailService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest lr) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(lr.getUsername(), lr.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = jwtUtils.generateJwtToken(auth);
        UserDetailsImpl ud = (UserDetailsImpl) auth.getPrincipal();
        List<String> roles = ud.getAuthorities().stream()
            .map(a -> a.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, ud.getId(), ud.getUsername(), ud.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest sr) {
        if (userRepo.existsByUsername(sr.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username taken"));
        }
        if (userRepo.existsByEmail(sr.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email in use"));
        }

        User user = new User();
        user.setUsername(sr.getUsername());
        user.setEmail(sr.getEmail());
        user.setPassword(encoder.encode(sr.getPassword()));

        Set<String> strRoles = sr.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role r = roleRepo.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(r);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        roles.add(roleRepo.findByName(ERole.ROLE_ADMIN).orElseThrow());
                        break;
                    case "mod":
                        roles.add(roleRepo.findByName(ERole.ROLE_MODERATOR).orElseThrow());
                        break;
                    default:
                        roles.add(roleRepo.findByName(ERole.ROLE_USER).orElseThrow());
                }
            });
        }

        user.setRoles(roles);
        userRepo.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered"));
    }

    // ✅ RESTABLECER CONTRASEÑA CON JWT VALIDADO
    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        Optional<User> optUser = userRepo.findByEmail(request.getEmail());
        if (optUser.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Usuario no encontrado"));
        }

        // 🔐 Validación JWT del token recibido
        if (!jwtUtils.validateJwtToken(request.getToken())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Token inválido o expirado"));
        }

        String tokenEmail = jwtUtils.getUserNameFromJwtToken(request.getToken());
        if (!tokenEmail.equals(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("El token no coincide con el email"));
        }

        User user = optUser.get();
        user.setPassword(encoder.encode(request.getPassword()));
        userRepo.save(user);

        return ResponseEntity.ok(new MessageResponse("Contraseña restablecida correctamente"));
    }

    // ✅ ENVÍO DE ENLACE POR CORREO
    @PostMapping("/forgot")
    public ResponseEntity<?> sendResetLink(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        Optional<User> userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email no registrado"));
        }

        String token = jwtUtils.generateTokenFromUsername(email);
        String resetLink = "http://localhost:4200/reset-password/" + email + "/" + token;

        mailService.send(email, "Recuperación de contraseña",
            "Haz clic en el siguiente enlace para restablecer tu contraseña:\n" + resetLink);

        return ResponseEntity.ok(new MessageResponse("Correo de recuperación enviado"));
    }
}
