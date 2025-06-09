package com.br.kchallenge.crud.service;

import com.br.kchallenge.crud.model.User; 
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;


public interface IUserService {
    User createUser(User user); 

    User getUserById(Long id);

    List<User> getAllUsers(); 

    User updateUser(Long id, User user); 

    void deleteUser(Long id); 

    UserDetails findByEmail(String email);
}