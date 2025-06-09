// package com.br.kchallenge.crud.service;

// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.ResponseEntity;

// import com.br.kchallenge.crud.controller.AuthenticationController;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;

// @SpringBootTest
// public class AuthenticationTest {

//     @Test
//     public void testLoginSuccess() {

//         AuthenticationController controller = Mockito.mock(AuthenticationController.class);
//         LoginRequest request = new LoginRequest("user", "password");
//         LoginResponse response = new LoginResponse("token123");
//         Mockito.when(controller.login(any(LoginRequest.class)))
//                 .thenReturn(ResponseEntity.ok(response));

//         ResponseEntity<LoginResponse> result = controller.login(request);

//         assertEquals(200, result.getStatusCodeValue());
//         assertEquals("token123", result.getBody().getToken());
//     }

//     @Test
//     public void testLoginFailure() {
//         AuthenticationController controller = Mockito.mock(AuthenticationController.class);
//         LoginRequest request = new LoginRequest("user", "wrongpassword");
//         Mockito.when(controller.login(any(LoginRequest.class)))
//                 .thenReturn(ResponseEntity.status(401).build());

//         ResponseEntity<LoginResponse> result = controller.login(request);

//         assertEquals(401, result.getStatusCodeValue());
//         assertNull(result.getBody());
//     }
// }
