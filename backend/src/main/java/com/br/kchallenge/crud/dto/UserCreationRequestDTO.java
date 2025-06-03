package com.br.kchallenge.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationRequestDTO extends UserDTO {
    private String password;
    // private String confirmPassword;
    
}
