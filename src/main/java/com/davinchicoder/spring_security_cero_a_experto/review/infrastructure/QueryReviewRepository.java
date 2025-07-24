package com.davinchicoder.spring_security_cero_a_experto.review.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
