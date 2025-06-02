package com.br.kchallenge.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private boolean active;
    private String password;    

}
