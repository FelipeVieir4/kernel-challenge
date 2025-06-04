package com.br.kchallenge.crud.service;

import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import com.br.kchallenge.crud.enums.RolesEnum;
import com.br.kchallenge.crud.model.User;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    private User userDefaut;

    @BeforeEach
    public void setUp() {
        userDefaut = new User();
        userDefaut.setName("Default User");
        userDefaut.setEmail("default" + System.currentTimeMillis() + "@email.com");
        userDefaut.setPassword("123456");
        userDefaut.setRole(RolesEnum.USER);
        userDefaut.setActive(true);
        userServiceImpl.createUser(userDefaut);
    }

    @Test
    public void creatUserTest() {

        User newUser = new User();
        newUser.setName("Default User");
        newUser.setEmail("default" + System.currentTimeMillis() + "@email.com");
        newUser.setPassword("123456");
        newUser.setRole(RolesEnum.USER);
        newUser.setActive(true);
        userServiceImpl.createUser(newUser);

        assertNotNull(newUser);
        assertNotNull(newUser.getId());
        assertEquals("Default User", newUser.getName());
    }

    @Test
    public void retrieveUserTest() {

        assertEquals("Default User", userDefaut.getName());
        assertNotNull(userDefaut);
        assertNotNull(userDefaut.getId());
        assertEquals("Default User", userDefaut.getName());

    }

    @Test
    public void updateUserTest() {

        User userToUpdate = new User();
        userToUpdate.setId(userDefaut.getId());
        userToUpdate.setName("Nome atualizado");
        userToUpdate.setEmail(userDefaut.getEmail());
        userToUpdate.setPassword(userDefaut.getPassword());
        userToUpdate.setRole(userDefaut.getRole());
        userToUpdate.setActive(userDefaut.isActive());
        // try to update an user that no exists
        assertThrowsExactly(NoSuchElementException.class, () -> userServiceImpl.updateUser(99999L, userToUpdate));
        User updatedUser = userServiceImpl.updateUser(userToUpdate.getId(), userToUpdate);
        assertEquals("Nome atualizado", updatedUser.getName());
    }

    @Test
    public void deleteUserTest() {

        assertThrowsExactly(NoSuchElementException.class, () -> userServiceImpl.deleteUser(99999L));

        User newUser = new User();
        newUser.setName("User to Delete");
        newUser.setEmail("default" + System.currentTimeMillis() + "@email.com");
        newUser.setPassword("123456");
        newUser.setRole(RolesEnum.USER);

        User createdUser = userServiceImpl.createUser(newUser);
        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        userServiceImpl.deleteUser(createdUser.getId());
        assertThrowsExactly(NoSuchElementException.class, () -> userServiceImpl.getUserById(createdUser.getId()));

    }
}
