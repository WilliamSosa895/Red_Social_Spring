package com.william.spring.security.postgresql.SpringBootSecurityPostgresApplication.java.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class ImageStorageService {
    private final Path uploadDir;

    @Autowired
    public ImageStorageService(@Value("${file.upload-dir}") String dir) {
        this.uploadDir = Paths.get(dir).toAbsolutePath().normalize();
        try { Files.createDirectories(uploadDir); }
        catch(IOException e) { throw new RuntimeException("No pude crear carpeta uploads", e); }
    }

    public String storeFile(MultipartFile file) {
        String fileName = UUID.randomUUID() + "-" + StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path target = uploadDir.resolve(fileName);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            // **Devolvemos la ruta pública con "/" al inicio**:
            return "/uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("No pude guardar " + fileName, e);
        }
    }
}
