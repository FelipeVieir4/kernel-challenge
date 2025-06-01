package com.br.kchallenge.crud.controller;

import com.br.kchallenge.crud.model.User; 
import com.br.kchallenge.crud.service.IUserService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta classe é um controlador REST que lida com requisições HTTP.
@RequestMapping("/users") // Define a rota.
public class UserController {

    @Autowired
    private final IUserService IUserService;

    public UserController(IUserService IUserService) {
        this.IUserService = IUserService; // Atribui o serviço de usuários à variável de instância.
    }

    @PostMapping // Mapeia requisições HTTP POST para este método.
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Recebe um objeto User no corpo da requisição e o cria no sistema.
        User createdUser = IUserService.createUser(user); // Chama o serviço para criar o usuário.
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // Retorna o usuário criado com status HTTP 201

    }

}
