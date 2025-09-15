package com.example.mybudget.service;

import com.example.mybudget.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest2 {

    @Autowired
    private UserService userService;

    UserDTO userDTO;
    @BeforeEach
    void setUp() {
        userDTO = UserDTO.builder()
                .loginId("serviceTest")
                .userBirthday("1999-06-29")
                .userName("user1")
                .password("password1")
                .email("email1@gmail.com")
                .build();

    }

    @Test
    void addUser() {
        userService.addUser(userDTO);

    }
}