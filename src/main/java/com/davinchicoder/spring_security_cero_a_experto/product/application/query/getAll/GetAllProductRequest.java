package com.davinchicoder.spring_security_cero_a_experto.product.application.query.getAll;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.Request;
import com.davinchicoder.spring_security_cero_a_experto.common.domain.PaginationQuery;
import com.davinchicoder.spring_security_cero_a_experto.product.domain.entity.ProductFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllProductRequest implements Request<GetAllProductResponse> {

    PaginationQuery paginationQuery;

    ProductFilter productFilter;

}
