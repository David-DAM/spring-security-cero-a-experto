package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database;

import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.entity.ProductEntity;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.mapper.ProductEntityMapper;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.repository.QueryProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    @Mock
    private QueryProductRepository repository;

    @Mock
    private ProductEntityMapper productEntityMapper;

    @InjectMocks
    private ProductRepositoryImpl productRepository;

    @Test
    void shouldNotReturnProductWhenNotFound() {

        Optional<Product> optionalProduct = productRepository.findById(1L);
        assertTrue(optionalProduct.isEmpty());
    }

    @Test
    void shouldReturnProductWhenFound() {
        when(repository.findById(1L)).thenReturn(Optional.of(new ProductEntity()));
        when(productEntityMapper.mapToProduct(any(ProductEntity.class))).thenReturn(Product.builder().id(1L).build());

        Optional<Product> optionalProduct = productRepository.findById(1L);
        assertTrue(optionalProduct.isPresent());
    }

}