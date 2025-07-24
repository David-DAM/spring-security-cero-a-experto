package com.davinchicoder.spring_security_cero_a_experto.common.infrastructure.util;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileUtils {

    public String saveProductImage(MultipartFile file) {

        String uniqueFileName;

        try (InputStream inputStream = file.getInputStream()) {

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            uniqueFileName = UUID.randomUUID().toString().concat("-").concat(fileName);

            Path path = Path.of("uploads/products/");

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Files.copy(inputStream, path.resolve(uniqueFileName), StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            throw new RuntimeException("Error al subir la imagen" + e.getMessage());
        }
        return uniqueFileName;
    }

}
