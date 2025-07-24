package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.repository;

import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QueryProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    Optional<ProductEntity> findByNameContaining(String name);

    List<ProductEntity> findAllByPriceBetween(Double priceAfter, Double priceBefore);

    @Query(value = """
            SELECT p
            FROM ProductEntity p
            WHERE p.name LIKE CONCAT('%', :name, '%')
            OR p.description LIKE CONCAT('%', :description, '%')
            OR p.price BETWEEN :priceAfter AND :priceBefore
            """
    )
    List<ProductEntity> findProductsDetails(String name, String description, Double priceAfter, Double priceBefore);

    boolean existsByName(String name);

    long countByPrice(Double price);

    Page<ProductEntity> findAll(Specification<ProductEntity> specification, Pageable pageable);

    @EntityGraph(attributePaths = {"productDetail", "reviews", "categories"})
    Optional<ProductEntity> findById(Long id);
}
