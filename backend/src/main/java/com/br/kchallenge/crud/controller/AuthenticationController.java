package com.br.kchallenge.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.http.client.HttpComponentsHttpAsyncClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.kchallenge.crud.dto.LoginDTO;
import com.br.kchallenge.crud.dto.RegisterDTO;
import com.br.kchallenge.crud.dto.UserRequestDTO;
import com.br.kchallenge.crud.dto.UserResponseDTO;
import com.br.kchallenge.crud.model.User;
import com.br.kchallenge.crud.service.IUserService;
import com.br.kchallenge.crud.utils.UserUtils;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    IUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> getLogin(@Validated @RequestBody LoginDTO loginDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                loginDTO.getPassword());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(
            @Validated @RequestBody RegisterDTO authenticationDTO) {

        if (userService.findByEmail(authenticationDTO.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User newUser = new User();
        newUser.setName(authenticationDTO.getName());
        newUser.setEmail(authenticationDTO.getEmail());
        newUser.setPassword(new BCryptPasswordEncoder().encode(authenticationDTO.getPassword()));
        User createdUser = userService.createUser(newUser);
        UserResponseDTO userResponseDTO = UserUtils.toUserResponseDTO(createdUser);

        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

}
