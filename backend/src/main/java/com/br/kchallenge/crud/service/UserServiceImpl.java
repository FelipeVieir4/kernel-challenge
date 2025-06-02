package com.br.kchallenge.crud.service;

import com.br.kchallenge.crud.dto.UserDTO;
import com.br.kchallenge.crud.model.User;
import com.br.kchallenge.crud.repository.IUserRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository; // Assigning the injected repository to the service
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update the fields of the existing user with the new values
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        // existingUser.setPassword(user.getPassword()); // Consider hashing the password before saving
        existingUser.setActive(user.isActive());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }


    @Override
    public User findByEmail(String email) {

        try {
            return userRepository.findByEmail(email);
        } catch (Exception e) {
            return null;
        }
    }
}