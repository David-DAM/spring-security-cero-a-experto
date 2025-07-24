package com.davinchicoder.spring_security_cero_a_experto.productDetail.infrastructure;

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
public class ProductDetailSeeder implements CommandLineRunner {

    private final QueryProductDetailRepository queryProductDetailRepository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        long count = queryProductDetailRepository.count();

        if (count == 0) {

            Resource resource = resourceLoader.getResource("classpath:products_details.json");

            List<ProductDetailEntity> productsDetailEntities = objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {
            });

            queryProductDetailRepository.saveAll(productsDetailEntities);
        }

    }
}
