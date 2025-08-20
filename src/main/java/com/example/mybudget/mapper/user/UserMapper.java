package com.example.mybudget.mapper.user;

import com.example.mybudget.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {

//    회원가입
    void insertUser(UserDTO userDTO);

    boolean existsByLoginId(String loginId);

    // 로그인
    Optional<Long> selectUserId(@Param("loginId") String loginId,
                               @Param("password") String password);

    int selectFailedLoginCount(String loginId);

    void updateFailedLoginCount(String loginId);

//    아이디/비번 찾기


}
