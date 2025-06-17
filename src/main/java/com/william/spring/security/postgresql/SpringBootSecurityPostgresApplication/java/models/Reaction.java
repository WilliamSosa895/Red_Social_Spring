package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "reactions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true, nullable = false)
    private EReaction type;

    public Reaction() { }
    public Reaction(EReaction type) { this.type = type; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public EReaction getType() { return type; }
    public void setType(EReaction type) { this.type = type; }
}
