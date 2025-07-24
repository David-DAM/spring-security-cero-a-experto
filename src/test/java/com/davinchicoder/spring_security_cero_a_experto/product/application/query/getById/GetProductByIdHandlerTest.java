package com.davinchicoder.spring_security_cero_a_experto.product.application.query.getById;

import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.exception.ProductNotFoundException;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.port.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetProductByIdHandlerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductByIdHandler handler;

    @Test
    void shouldReturnProductWhenFound() {
        // Arrange
        long productId = 1L;
        Product mockProduct = Product.builder().id(productId).build();
        GetProductByIdRequest request = new GetProductByIdRequest(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        // Act
        GetProductByIdResponse response = handler.handle(request);

        // Assert
        assertNotNull(response);
        assertEquals(mockProduct, response.getProduct());
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        // Arrange
        long productId = 1L;
        GetProductByIdRequest request = new GetProductByIdRequest(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProductNotFoundException.class, () -> handler.handle(request));
    }
}