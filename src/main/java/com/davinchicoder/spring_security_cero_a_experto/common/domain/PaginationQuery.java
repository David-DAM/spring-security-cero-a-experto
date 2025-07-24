package com.davinchicoder.spring_security_cero_a_experto.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaginationQuery {

    private int page;
    private int size;
    private String sortBy;
    private String direction;

}
