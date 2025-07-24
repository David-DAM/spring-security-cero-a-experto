package com.davinchicoder.spring_security_cero_a_experto.product.application.command.update;

import com.davinchicoder.spring_security_cero_a_experto.category.domain.Category;
import com.davinchicoder.spring_security_cero_a_experto.category.infrastructure.CategoryEntityMapper;
import com.davinchicoder.spring_security_cero_a_experto.category.infrastructure.QueryCategoryRepository;
import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.RequestHandler;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.exception.ProductNotFoundException;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.port.ProductRepository;
import com.davinchicoder.spring_security_cero_a_experto.productDetail.domain.ProductDetail;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackOn = Exception.class)
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest, Void> {

    private final ProductRepository productRepository;
    private final QueryCategoryRepository queryCategoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public Void handle(UpdateProductRequest request) {

        log.info("Updating product with id {}", request.getId());

        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new ProductNotFoundException(request.getId()));

        ProductDetail productDetail = product.getProductDetail();

        productDetail.setProvider(request.getProvider());

        product.getReviews().add(request.getReview());

        Category category = queryCategoryRepository.findById(request.getCategoryId())
                .map(categoryEntityMapper::mapToCategory)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.getCategories().add(category);

        productRepository.upsert(product);

        log.info("Updated product with id {}", request.getId());

        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
}
