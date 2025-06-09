package com.br.kchallenge.crud.controller;

import com.br.kchallenge.crud.dto.UserRequestDTO;
import com.br.kchallenge.crud.dto.UserResponseDTO;
import com.br.kchallenge.crud.enums.RolesEnum;
import com.br.kchallenge.crud.model.User;
import com.br.kchallenge.crud.service.IUserService;
import com.br.kchallenge.crud.utils.UserUtils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final IUserService IUserService;

    public UserController(IUserService IUserService) {
        this.IUserService = IUserService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @Validated @RequestBody UserRequestDTO userRequestDTO) {

        if (IUserService.findByEmail(userRequestDTO.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User newUser = new User();
        newUser.setName(userRequestDTO.getName());
        newUser.setEmail(userRequestDTO.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(userRequestDTO.getPassword()));
        User createdUser = IUserService.createUser(newUser);
        UserResponseDTO userResponseDTO = UserUtils.toUserResponseDTO(createdUser);
        if (userResponseDTO == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOList = IUserService.getAllUsers().stream()
                .map(UserUtils::toUserResponseDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(userResponseDTOList, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = IUserService.getUserById(id);
        UserResponseDTO userResponseDTO = UserUtils.toUserResponseDTO(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
            @Validated @RequestBody UserRequestDTO userRequestDTO) {

        User existingUser = IUserService.getUserById(id);
        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingUser.setName(userRequestDTO.getName());
        existingUser.setEmail(userRequestDTO.getEmail());
        existingUser.setPassword(userRequestDTO.getPassword()); // tem que criptografar a senha antes de salvar

        User userUpdated = IUserService.updateUser(id, existingUser);

        UserResponseDTO userResponseDTO = UserUtils.toUserResponseDTO(userUpdated);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> patchUser(@PathVariable Long id,
            @Validated @RequestBody UserRequestDTO userRequestDTO) {
        User user = IUserService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (userRequestDTO.getName() != null)
            user.setName(userRequestDTO.getName());
        if (userRequestDTO.getEmail() != null)
            user.setEmail(userRequestDTO.getEmail());

        User userUpdated = IUserService.updateUser(id, user);

        return new ResponseEntity<>(UserUtils.toUserResponseDTO(userUpdated), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = IUserService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        IUserService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/changeStatus")
    public ResponseEntity<UserResponseDTO> changeUserStatus(@PathVariable Long id) {
        User user = IUserService.getUserById(id);

        if (user.getRole() != RolesEnum.ADMIN) {
            user.setActive(!user.isActive());
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        UserResponseDTO updatedUserResponseDTO = UserUtils.toUserResponseDTO(IUserService.updateUser(id, user));

        return new ResponseEntity<>(updatedUserResponseDTO, HttpStatus.OK);
    }

}
