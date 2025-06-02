package com.br.kchallenge.crud.controller;

import com.br.kchallenge.crud.dto.UserDTO;
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
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody UserDTO userDTO) {

        if (IUserService.findByEmail(userDTO.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User newUser = new User();
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword()); // tem que criptografar a senha antes de salvar

        User createdUser = IUserService.createUser(newUser);

        UserDTO userToReturnDTO = toUserDTO(createdUser);

        return new ResponseEntity<>(userToReturnDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOList = IUserService.getAllUsers().stream()
                .map(this::toUserDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(userDTOList, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = IUserService.getUserById(id);
        UserDTO userDTO = toUserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Validated @RequestBody UserDTO userDTO) {

        User existingUser = IUserService.getUserById(id);
        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPassword(userDTO.getPassword()); // tem que criptografar a senha antes de salvar

        User userUpdated = IUserService.updateUser(id, existingUser);

        UserDTO userToReturnDTO = toUserDTO(userUpdated);
        return new ResponseEntity<>(userToReturnDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> patchUser(@PathVariable Long id, @Validated @RequestBody UserDTO userDTO) {
        User user = IUserService.getUserById(id);
        if (user == null)
            return ResponseEntity.notFound().build();

        if (userDTO.getName() != null)
            user.setName(userDTO.getName());
        if (userDTO.getEmail() != null)
            user.setEmail(userDTO.getEmail());

        User updated = IUserService.updateUser(id, user);
        return ResponseEntity.ok(toUserDTO(updated));
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
    public ResponseEntity<UserDTO> changeUserStatus(@PathVariable Long id) {
        User user = IUserService.getUserById(id);

        if (user.getRole() != RolesEnum.ADMIN) {
            user.setActive(!user.isActive());
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        UserDTO updated = toUserDTO(IUserService.updateUser(id, user));

        return ResponseEntity.ok(updated);
    }

    // UTILITÁRIOS
    private UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setActive(user.isActive());
        return dto;
    }
}
