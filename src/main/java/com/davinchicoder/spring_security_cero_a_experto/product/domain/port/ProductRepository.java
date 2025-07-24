package com.davinchicoder.spring_security_cero_a_experto.product.domain.port;

import com.davinchicoder.spring_security_cero_a_experto.common.domain.PaginationQuery;
import com.davinchicoder.spring_security_cero_a_experto.common.domain.PaginationResult;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.Product;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.ProductFilter;

import java.util.Optional;

public interface ProductRepository {

    Product upsert(Product product);

    Optional<Product> findById(Long id);

    PaginationResult<Product> findAll(PaginationQuery paginationQuery, ProductFilter productFilter);

    void deleteById(Long id);
}
