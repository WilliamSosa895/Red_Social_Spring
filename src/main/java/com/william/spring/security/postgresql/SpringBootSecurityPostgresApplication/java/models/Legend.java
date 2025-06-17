package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="legends")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Legend {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max=100)
    private String name;

    @NotBlank @Size(max=50)
    private String team;

    @Size(max=30)
    private String position;

    private String photoUrl;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
}