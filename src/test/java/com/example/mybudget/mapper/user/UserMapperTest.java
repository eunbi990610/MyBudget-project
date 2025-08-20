package com.example.mybudget.mapper.user;

import com.example.mybudget.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    UserDTO userDTO;
    @BeforeEach
    void setUp() {
        userDTO = UserDTO.builder()
                .loginId("user1TestId")
                .userBirthday("1999-06-29")
                .userName("user1")
                .password("password1")
                .email("email1@gmail.com")
                .build();
    }

    @Test
    void insertUser() {
        System.out.println("insert user");
        userMapper.insertUser(userDTO);

        System.out.println("select user");
        Long userId = userMapper.selectUserId(userDTO.getLoginId(), userDTO.getPassword())
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다. "));

// org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.example.mybudget.mapper.user.UserMapper.selectUserId
// mybatis가 sql 매핑을 찾지 못해 발생한 에러
// xml의 쿼리문 id와 mapper 인터페이스의 메서드 명이 동일한 지 확인하기!

// Error querying database.  Cause: org.apache.ibatis.type.TypeException: Could not set parameters for mapping: ParameterMapping{property='loginId', mode=IN, javaType=class java.lang.Long, jdbcType=null, numericScale=null, resultMapId='null', jdbcTypeName='null', expression='null'}. Cause: org.apache.ibatis.type.TypeException: Error setting non null for parameter #1 with JdbcType null . Try setting a different JdbcType for this parameter or a different configuration property. Cause: java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Long (java.lang.String and java.lang.Long are in module java.base of loader 'bootstrap')
// xml에서 resultType = long 으로 작성해야하는데 parameterType=long 으로 하는 바람에 String 타입인 loginId를 Long으로 인식하여 발생한 오류

        System.out.println("userId = " + userId);

        System.out.println("selectLoginId count" );
        boolean existsByLoginId= userMapper.existsByLoginId(userDTO.getLoginId());
        System.out.println("loginIdCount = " + existsByLoginId);

    }

    @Test
    void failTest(){

        userMapper.insertUser(userDTO);
        System.out.println("insert 1 success");
        userMapper.insertUser(userDTO);
        System.out.println("insert 2 success");
    }




}