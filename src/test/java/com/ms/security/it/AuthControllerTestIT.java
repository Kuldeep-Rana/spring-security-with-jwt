package com.ms.security.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.security.config.SecurityConfig;
import com.ms.security.controller.AuthController;
import com.ms.security.db.config.TestDbConfig;
import com.ms.security.dto.AuthenticationRequest;
import com.ms.security.dto.AuthenticationResponse;
import com.ms.security.entity.User;
import com.ms.security.filter.JwtRequestFilter;
import com.ms.security.service.CustomUserDetailsService;
import com.ms.security.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDbConfig.class, SecurityConfig.class, AuthControllerTestIT.HelperContextConfiguration.class})
public class AuthControllerTestIT {
    @Autowired
    private AuthController authController;

    @Test
    void test_authenticate() throws Exception {

       ResponseEntity responseEntity = authController.createAuthenticationToken(new AuthenticationRequest("manager","mgp"));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        AuthenticationResponse response = (AuthenticationResponse) responseEntity.getBody();
        assertNotNull(response.getJwt());
    }

    @Test
    void test_saveUser() throws Exception {
        User user = getUser();
         ResponseEntity responseEntity = authController.saveUser(user);
         assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
         String message = (String) responseEntity.getBody();
         assertNotNull(message);
         assertEquals("User created", message);
    }


    @Configuration

    public static class HelperContextConfiguration{

        @Bean
        DataSource h2DataSource() {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
                    .addScript("db/sql/home/create.sql")
                    .addScript("db/sql/home/insert.sql")
                    .build();
            return db;
        }

        @Bean
        CustomUserDetailsService customUserDetailsService(){
            return new CustomUserDetailsService();
        }

        @Bean
        JwtRequestFilter jwtRequestFilter(){
            return new JwtRequestFilter();
        }

        @Bean
        JwtUtil jwtUtil(){
            return new JwtUtil();
        }

        @Bean
        AuthController authController(){
            return new AuthController();
        }
    }

    private User getUser() throws JsonProcessingException {
        String s = "{\n" +
                "    \"username\": \"testuser\",\n" +
                "    \"password\": \"testp\",\n" +
                "    \"roles\": [\n" +
                "        {\n" +
                "            \"name\": \"ROLE_USER\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        return new ObjectMapper().readValue(s,User.class);
    }

}




