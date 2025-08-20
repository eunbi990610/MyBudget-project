package com.example.mybudget.service;

import com.example.mybudget.dto.UserDTO;
import com.example.mybudget.mapper.user.BudgetMapper;
import com.example.mybudget.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BudgetMapper budgetMapper;

//    회원가입
    public void addUser(UserDTO userDTO){

        if(userMapper.existsByLoginId(userDTO.getLoginId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용 중인 아이디");
        }
        userMapper.insertUser(userDTO);

        List<String> defaultCategories = List.of("고정비","적금","교통비","기타");
        budgetMapper.insertDefaultCategories(userDTO.getUserId(),defaultCategories);

    }

//    로그인
    public Long findUserId(String loginId, String password){
        return userMapper.selectUserId(loginId, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아아디 또는 비밀번호가 일치하지 않습니다."));

    }




}
