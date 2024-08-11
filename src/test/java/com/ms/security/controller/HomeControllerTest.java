package com.ms.security.controller;

import com.ms.security.service.CustomUserDetailsService;
import com.ms.security.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class})
@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomUserDetailsService customUserDetailsService;

    @MockBean
    JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        // Do nothing
    }
    @Test
    @WithMockUser(roles = "USER")
    void test_hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void test_admin() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("i am the admin"));
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    void test_admin_forbidden() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    void test_manager() throws Exception {
        mockMvc.perform(get("/manager"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("i am the manager"));
    }

}