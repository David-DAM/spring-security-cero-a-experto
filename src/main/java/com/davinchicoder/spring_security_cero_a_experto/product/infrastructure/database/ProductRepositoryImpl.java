package com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database;

import com.davinchicoder.spring_security_cero_a_experto.common.domain.PaginationQuery;
import com.davinchicoder.spring_security_cero_a_experto.common.domain.PaginationResult;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.ProductFilter;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.port.ProductRepository;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.entity.ProductEntity;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.mapper.ProductEntityMapper;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.repository.QueryProductRepository;
import com.davinchicoder.spring_security_cero_a_experto.product.infrastructure.database.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private final QueryProductRepository repository;

    private final ProductEntityMapper productEntityMapper;


    @Override
    public Product upsert(Product product) {
        ProductEntity productEntity = productEntityMapper.mapToProductEntity(product);
        ProductEntity save = repository.save(productEntity);
        return productEntityMapper.mapToProduct(save);
    }

    @Cacheable(value = "products", key = "#id")
    @Override
    public Optional<Product> findById(Long id) {
        log.info("Finding product with id {}", id);
        return repository.findById(id).map(productEntityMapper::mapToProduct);
    }

    @Override
    public PaginationResult<Product> findAll(PaginationQuery paginationQuery, ProductFilter productFilter) {

        PageRequest pageRequest = PageRequest.of(
                paginationQuery.getPage(),
                paginationQuery.getSize(),
                Sort.by(Sort.Direction.fromString(paginationQuery.getDirection()), paginationQuery.getSortBy())
        );

        Specification<ProductEntity> specification = Specification.allOf(
                ProductSpecification.byName(productFilter.getName())
                        .and(ProductSpecification.byDescription(productFilter.getDescription())
                                .and(ProductSpecification.byPrice(productFilter.getPriceMin(), productFilter.getPriceMax())))
        );

        Page<ProductEntity> page = repository.findAll(specification, pageRequest);

        return new PaginationResult<>(
                page.getContent().stream().map(productEntityMapper::mapToProduct).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @CacheEvict(value = "products", key = "#id")
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
