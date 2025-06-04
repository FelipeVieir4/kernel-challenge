package com.br.kchallenge.crud.service;

import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
import com.br.kchallenge.crud.enums.RolesEnum;
import com.br.kchallenge.crud.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    public void creatUserTest() {
        User user = new User();
        user.setName("Teste");
        user.setEmail("teste@email.com");
        user.setPassword("123456");
        user.setRole(RolesEnum.USER);

        User createdUser = userServiceImpl.createUser(user);

        System.out.println(createdUser.toString);

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
    }

    @Test
    public void retrieveUserTest() {
        User user = new User();
        user.setName("Teste");
        user.setEmail("teste@email.com");
        user.setPassword("123456");
        user.setRole(RolesEnum.USER);

        User userCreated = userServiceImpl.createUser(user);

        assertEquals("Teste", userCreated.getName());

    }

    @Test
    public void updateUserTest() {

        User user = new User();
        user.setName("Teste");
        user.setEmail("teste@email.com");
        user.setPassword("123456");
        user.setRole(RolesEnum.USER);

        User createdUser = userServiceImpl.createUser(user);

        User userToUpdate = new User();
        userToUpdate.setId(createdUser.getId());
        userToUpdate.setName("Nome atualizado");
        userToUpdate.setEmail(createdUser.getEmail());
        userToUpdate.setPassword(createdUser.getPassword());
        userToUpdate.setRole(createdUser.getRole());
        userToUpdate.setActive(createdUser.isActive());

        // try to update an user that no exists
        assertThrowsExactly(NoSuchElementException.class, () -> userServiceImpl.updateUser(99999L, userToUpdate));

        User updatedUser = userServiceImpl.updateUser(userToUpdate.getId(), userToUpdate);

        assertEquals("Nome atualizado", updatedUser.getName());

    }
}
