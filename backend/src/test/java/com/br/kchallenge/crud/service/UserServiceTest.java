package com.br.kchallenge.crud.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.br.kchallenge.crud.enums.RolesEnum;
import com.br.kchallenge.crud.model.User;
import com.br.kchallenge.crud.repository.IUserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Autowired
    private IUserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("Teste");
        user.setEmail("teste@email.com");
        user.setPassword("123456");
        user.setRole(RolesEnum.USER);

        when(userRepository.save(user))
                .thenReturn(new User(1L, "Teste", "teste@email.com", null, RolesEnum.USER, true));

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals("Teste", createdUser.getName());

        // verify(userRepository, times(2)).save(user);
    }
}
