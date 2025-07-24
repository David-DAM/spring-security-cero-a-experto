package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database;

import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.entity.ProductEntity;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.repository.QueryProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ProductRepositoryImplJpaTest {

    @Autowired
    private QueryProductRepository repository;

    @Test
    void shouldNotReturnProductWhenNotFound() {

        Optional<ProductEntity> optionalProduct = repository.findById(1L);
        assertTrue(optionalProduct.isEmpty());
    }

    @Test
    void shouldReturnProductWhenFound() {

        ProductEntity productEntity = new ProductEntity();
        ProductEntity save = repository.save(productEntity);

        Optional<ProductEntity> optionalProduct = repository.findById(save.getId());
        assertTrue(optionalProduct.isPresent());
    }

}