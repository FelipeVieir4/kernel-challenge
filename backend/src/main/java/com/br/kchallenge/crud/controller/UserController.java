package com.br.kchallenge.crud.controller;

import com.br.kchallenge.crud.dto.UserCreationRequestDTO;
import com.br.kchallenge.crud.dto.UserResponseDTO;
import com.br.kchallenge.crud.enums.RolesEnum;
import com.br.kchallenge.crud.model.User;
import com.br.kchallenge.crud.service.IUserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

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
            @Validated @RequestBody UserCreationRequestDTO userCreationRequestDTO) {

        if (IUserService.findByEmail(userCreationRequestDTO.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User newUser = new User();
        newUser.setName(userCreationRequestDTO.getName());
        newUser.setEmail(userCreationRequestDTO.getEmail());
        newUser.setPassword(userCreationRequestDTO.getPassword()); // tem que criptografar a senha antes de salvar

        User createdUser = IUserService.createUser(newUser);

        UserResponseDTO userResponseDTO = toUserResponseDTO(createdUser);

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOList = IUserService.getAllUsers().stream()
                .map(this::toUserResponseDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(userResponseDTOList, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = IUserService.getUserById(id);
        UserResponseDTO userResponseDTO = toUserResponseDTO(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id,
            @Validated @RequestBody UserCreationRequestDTO userCreationRequestDTO) {

        User existingUser = IUserService.getUserById(id);
        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingUser.setName(userCreationRequestDTO.getName());
        existingUser.setEmail(userCreationRequestDTO.getEmail());
        existingUser.setPassword(userCreationRequestDTO.getPassword()); // tem que criptografar a senha antes de salvar

        User userUpdated = IUserService.updateUser(id, existingUser);

        UserResponseDTO userResponseDTO = toUserResponseDTO(userUpdated);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> patchUser(@PathVariable Long id,
            @Validated @RequestBody UserCreationRequestDTO userCreationRequestDTO) {
        User user = IUserService.getUserById(id);
        if (user == null)
            return ResponseEntity.notFound().build();

        if (userCreationRequestDTO.getName() != null)
            user.setName(userCreationRequestDTO.getName());
        if (userCreationRequestDTO.getEmail() != null)
            user.setEmail(userCreationRequestDTO.getEmail());

        User userUpdated = IUserService.updateUser(id, user);

        return ResponseEntity.ok(toUserResponseDTO(userUpdated));
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
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UserResponseDTO updatedUserResponseDTO = toUserResponseDTO(IUserService.updateUser(id, user));

        return ResponseEntity.ok(updatedUserResponseDTO);
    }

    // UTILITÁRIOS
    private UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO userResponseSTO = new UserResponseDTO();
        userResponseSTO.setId(user.getId());
        userResponseSTO.setName(user.getName());
        userResponseSTO.setEmail(user.getEmail());
        userResponseSTO.setActive(user.isActive());
        return userResponseSTO;
    }
}
