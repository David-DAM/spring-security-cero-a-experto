package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.mapper;

import com.davinchicoder.spring_security_cero_a_experto.category.domain.Category;
import com.davinchicoder.spring_security_cero_a_experto.category.infrastructure.CategoryEntity;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.entity.ProductEntity;
import com.davinchicoder.spring_security_cero_a_experto.review.domain.Review;
import com.davinchicoder.spring_security_cero_a_experto.review.infrastructure.ReviewEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductEntityMapper {


    @Mapping(target = "productDetail.product", ignore = true)
    ProductEntity mapToProductEntity(Product product);

    @Mapping(target = "productDetail.product", ignore = true)
    Product mapToProduct(ProductEntity productEntity);

    @Mapping(target = "product", ignore = true)
    Review maptoReview(ReviewEntity reviewEntity);

    @Mapping(target = "product", ignore = true)
    ReviewEntity mapToReviewEntity(Review review);

    @Mapping(target = "products", ignore = true)
    Category mapToCategory(CategoryEntity categoryEntity);

    @Mapping(target = "products", ignore = true)
    CategoryEntity mapToCategoryEntity(Category category);

    @AfterMapping
    default void linkReviews(@MappingTarget ProductEntity productEntity) {
        if (productEntity.getReviews() != null) {
            productEntity.getReviews().forEach(r -> r.setProduct(productEntity));
        }
    }

}
