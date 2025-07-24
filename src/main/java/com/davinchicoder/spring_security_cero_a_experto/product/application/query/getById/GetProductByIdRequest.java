package com.davinchicoder.spring_security_cero_a_experto.product.application.query.getById;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductByIdRequest implements Request<GetProductByIdResponse> {

    private Long id;
}
