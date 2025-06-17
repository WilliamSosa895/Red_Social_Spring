package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")    public String all()    { return "Public"; }
  @GetMapping("/user")   @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")    public String user() { return "User"; }
  @GetMapping("/mod")    @PreAuthorize("hasRole('MODERATOR')")    public String mod()   { return "Mod"; }
  @GetMapping("/admin")  @PreAuthorize("hasRole('ADMIN')")    public String admin() { return "Admin"; }
}