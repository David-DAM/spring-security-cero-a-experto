package com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.api.dto;

import com.davinchicoder.spring_security_cero_a_experto.user.infrastructure.database.annotation.MaskData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    @MaskData
    private String email;
    private String firstName;
    private String lastName;

}
