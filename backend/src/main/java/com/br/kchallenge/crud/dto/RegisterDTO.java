package com.br.kchallenge.crud.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String name;
    private String email;
    private String password;

}
