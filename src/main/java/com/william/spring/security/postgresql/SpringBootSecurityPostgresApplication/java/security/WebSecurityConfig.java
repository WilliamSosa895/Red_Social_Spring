package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.security.jwt.AuthEntryPointJwt;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.security.jwt.AuthTokenFilter;
import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    private final UserDetailsServiceImpl uds;
    private final AuthEntryPointJwt entry;

    public WebSecurityConfig(UserDetailsServiceImpl uds, AuthEntryPointJwt entry) {
        this.uds = uds;
        this.entry = entry;
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(uds);
        p.setPasswordEncoder(passwordEncoder());
        return p;
    }

    @Bean
    public AuthenticationManager authManager(
        org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration cfg
    ) throws Exception {
        return cfg.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .cors().and()                      // Enable CORS
          .csrf().disable()
          .exceptionHandling().authenticationEntryPoint(entry).and()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/api/auth/**", "/uploads/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
              .anyRequest().authenticated()
          )
          .authenticationProvider(authProvider())
          .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
