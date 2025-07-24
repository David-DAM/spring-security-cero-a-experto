package com.davinchicoder.spring_security_cero_a_experto.category.infrastructure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class CategorySeeder implements CommandLineRunner {

    private final QueryCategoryRepository queryCategoryRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        long count = queryCategoryRepository.count();

        if (count == 0) {

            Resource resource = resourceLoader.getResource("classpath:categories.json");

            List<CategoryEntity> categoryEntities = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {
            });

            queryCategoryRepository.saveAll(categoryEntities);
        }

    }
}
