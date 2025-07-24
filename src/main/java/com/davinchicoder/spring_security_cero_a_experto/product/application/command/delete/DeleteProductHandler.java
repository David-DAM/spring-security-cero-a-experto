package com.davinchicoder.spring_security_cero_a_experto.product.application.command.delete;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.RequestHandler;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.port.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest, Void> {

    private final ProductRepository productRepository;

    @Override
    public Void handle(DeleteProductRequest request) {

        log.info("Deleting product with id {}", request.getId());

        productRepository.deleteById(request.getId());

        log.info("Deleted product with id {}", request.getId());

        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
}
