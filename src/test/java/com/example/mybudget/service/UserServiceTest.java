package com.example.mybudget.service;

import com.example.mybudget.dto.UserDTO;
import com.example.mybudget.mapper.user.UserMapper;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    UserDTO userDto;

    @Autowired UserService userService;


    @BeforeEach
    void setUp() {
        userDto = UserDTO.builder()
                .userName("양은비")
                .userBirthday("1999-06-10")
                .loginId("user1TestId")
                .password("password1")
                .email("email1@gmail.com")
                .build();
    }

    @Test
    void addUser() {
        userService.addUser(userDto);

    }
    @Test
    void failedAddUser(){
        userService.addUser(userDto);
        System.out.println("첫번째 회원 회원가입 성공");
//        여기서 오류 발생 성공!
//        userService.addUser(userDto);
//        System.out.println("두번째 회원 회원가입 성공");

    }


    @Test
    void findUserId() {
        userService.addUser(userDto);
        userService.findUserId(userDto.getLoginId(), userDto.getPassword());
        System.out.println("로그인 성공!");

    }
}