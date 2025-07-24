package com.davinchicoder.spring_security_cero_a_experto.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginationResult<T> {

    private List<T> content;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;

}
