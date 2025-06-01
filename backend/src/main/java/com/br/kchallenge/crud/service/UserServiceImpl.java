package com.br.kchallenge.crud.service;

import com.br.kchallenge.crud.model.User; // Importing the User model class
import com.br.kchallenge.crud.repository.IUserRepository; // Importing the User repository interface
// import org.springframework.beans.factory.annotation.Autowired; // Importing Spring's Autowired annotation
import org.springframework.stereotype.Service; // Importing Spring's Service annotation


@Service // Indicates that this class is a Spring service component
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository; // Repository for User entity

    //@Autowired // Automatically injects the IUserRepository bean
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository; // Assigning the injected repository to the service
    }

    @Override
    public User createUser(User user) {
        // LÓGICA IMPORTANTE DE CRIPTOGRAFIA DE SENHA VIRÁ AQUI EM BREVE!
        return userRepository.save(user); // Saves the user entity to the database
    }
}