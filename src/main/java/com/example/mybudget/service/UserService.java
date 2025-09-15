package com.example.mybudget.service;

import com.example.mybudget.dto.BudgetDTO;
import com.example.mybudget.dto.CategoryDTO;
import com.example.mybudget.dto.CategoryType;
import com.example.mybudget.dto.UserDTO;
import com.example.mybudget.mapper.user.BudgetMapper;
import com.example.mybudget.mapper.user.PeriodMapper;
import com.example.mybudget.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BudgetMapper budgetMapper;
    private final PeriodMapper periodMapper;

//    회원가입
    public void addUser(UserDTO userDTO){

        if(userMapper.existsByLoginId(userDTO.getLoginId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 사용 중인 아이디");
        }
        userMapper.insertUser(userDTO);

        Long userId = userDTO.getUserId();
        LocalDate now = LocalDate.now();
        Long periodId = periodMapper.selectPeriodId(now.getYear(),now.getMonthValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "periodId 없음!"));

        List<String> defaultCategory = List.of("고정비","적금","교통비","기타");
        defaultCategory.forEach(category -> {
            CategoryDTO categoryDTO;
            categoryDTO = CategoryDTO.builder()
                    .userId(userId)
                    .categoryType(CategoryType.EXPENSE)
                    .categoryName(category)
                    .build();

            BudgetDTO budgetDTO;
            budgetDTO = BudgetDTO.builder()
                    .periodId(periodId)
                    .userId(userId)
                    .build();
           budgetMapper.insertCategory(categoryDTO);
           budgetDTO.setCategoryId(categoryDTO.getCategoryId());
           budgetMapper.insertBudget(budgetDTO);
        });
    }

//    로그인
    public Long findUserId(String loginId, String password){
        return userMapper.selectUserId(loginId, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아아디 또는 비밀번호가 일치하지 않습니다."));

    }




}
