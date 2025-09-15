
package com.example.dynamicforms;

import com.example.dynamicforms.controller.AuthController;
import com.example.dynamicforms.dto.LoginRequest;
import com.example.dynamicforms.dto.RegisterRequest;
import com.example.dynamicforms.service.AuthService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
public class AuthControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    AuthService authService;

    @Test
    void testRegister() throws Exception {
        RegisterRequest req = new RegisterRequest();
        req.setEmail("test@example.com");
        req.setPassword("password");

        when(authService.registerAdmin(any())).thenReturn(null); // Mock behavior

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"admin@example.com\", \"password\":\"admin123\"}"))
                .andExpect(status().isOk());
    }
}