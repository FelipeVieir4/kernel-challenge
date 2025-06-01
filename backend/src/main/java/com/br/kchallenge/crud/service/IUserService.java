package com.br.kchallenge.crud.service;

import com.br.kchallenge.crud.model.User; // Importing the User model class

public interface IUserService {
    User createUser(User user); // Method to create a new user

    // User getUserById(Long id); // Method to retrieve a user by ID

    // List<User> getAllUsers(); // Method to retrieve all users

    // User updateUser(Long id, User user); // Method to update an existing user

    // void deleteUser(Long id); // Method to delete a user by ID

    // Optional<User> User getUserByEmail(String email); // Method to retrieve a user by email
}