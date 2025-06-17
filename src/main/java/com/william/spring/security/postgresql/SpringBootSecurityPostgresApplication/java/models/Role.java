package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
@Entity
@Table(name="roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Role {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length=20)
    private ERole name;
    public Role(){}
    public Role(ERole name) {
    this.name = name;
}


    // Getters & Setters...
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public ERole getName() { return name; }
    public void setName(ERole name) { this.name = name; }
}