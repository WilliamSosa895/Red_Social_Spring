package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;

import java.util.List;

import com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.models.Legend;

public interface LegendService {
    Legend create(Legend legend);
    Legend update(Long id, Legend legend);
    void delete(Long id);
    Legend getById(Long id);
    List<Legend> listAll();
}