package com.br.kchallenge.crud.utils;

import com.br.kchallenge.crud.dto.UserResponseDTO;
import com.br.kchallenge.crud.model.User;

public class UserUtils {
    public static UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO userResponseSTO = new UserResponseDTO();
        userResponseSTO.setId(user.getId());
        userResponseSTO.setName(user.getName());
        userResponseSTO.setEmail(user.getEmail());
        userResponseSTO.setActive(user.isActive());
        return userResponseSTO;
    }
}
