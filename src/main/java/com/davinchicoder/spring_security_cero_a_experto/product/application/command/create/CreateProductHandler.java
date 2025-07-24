package com.davinchicoder.spring_security_cero_a_experto.product.application.command.create;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.RequestHandler;
import com.davinchicoder.spring_security_cero_a_experto.common.infrastructure.util.FileUtils;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateProductHandler implements RequestHandler<CreateProductRequest, CreateProductResponse> {

    private final ProductRepository productRepository;
    private final FileUtils fileUtils;

    @Override
    public CreateProductResponse handle(CreateProductRequest request) {

        log.info("Creating product");

        String uniqueFileName = fileUtils.saveProductImage(request.getFile());

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .image(uniqueFileName)
                .build();

        Product storedProduct = productRepository.upsert(product);

        log.info("Created product with id {}", storedProduct.getId());

        return new CreateProductResponse(storedProduct);
    }


    @Override
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }
}
