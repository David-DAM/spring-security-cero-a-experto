package com.davinchicoder.spring_security_cero_a_experto.product.application.command.delete;

import com.davinchicoder.spring_security_cero_a_experto.common.application.mediator.Request;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteProductRequest implements Request<Void> {

    private Long id;
}
