package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.api;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.Mediator;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.api.mapper.ProductMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private Mediator mediator;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductController productController;

//    @Test
//    public void getAllProducts() {
//
//        GetAllProductResponse getAllProductResponse = new GetAllProductResponse(List.of(
//                Product.builder().id(1L).build(),
//                Product.builder().id(2L).build()
//        ));
//
//        when(mediator.dispatch(new GetAllProductRequest())).thenReturn(getAllProductResponse);
//
//        ProductDto productDto = new ProductDto();
//        productDto.setId(1L);
//
//        when(productMapper.mapToProductDto(any(Product.class))).thenReturn(productDto);
//
//        ResponseEntity<List<ProductDto>> response = productController.getAllProducts(0, 5);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//
//        List<ProductDto> products = response.getBody();
//        assertEquals(2, products.size());
//    }

}