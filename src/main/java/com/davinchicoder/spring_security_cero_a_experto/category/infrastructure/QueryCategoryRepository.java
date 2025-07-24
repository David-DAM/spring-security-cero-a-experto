package com.davinchicoder.spring_security_cero_a_experto.category.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryCategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
