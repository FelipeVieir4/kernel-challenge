package com.br.kchallenge.crud.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private String email;
    private String password;

}
