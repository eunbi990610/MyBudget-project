package com.example.mybudget.service;

import com.example.mybudget.dto.CategoryDTO;
import com.example.mybudget.dto.CategoryType;
import com.example.mybudget.dto.UserDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class budgetServiceTest {

    UserDTO userDto;
    CategoryDTO categoryDto;

    @Autowired
    UserService userService;
    @Autowired
    BudgetService budgetService;

    @BeforeEach
    void setUp() {
        userDto = UserDTO.builder()
                .userName("양은비")
                .userBirthday("1999-06-10")
                .loginId("user1TestId")
                .password("password1")
                .email("email1@gmail.com")
                .build();

        categoryDto = CategoryDTO.builder()
                .categoryName("<category test>")
                .userId(6L)
                .categoryType(CategoryType.EXPENSE)
                .build();
    }

    @Test
    void findAllCategories() {
        userService.addUser(userDto);

        List<CategoryDTO> allCategories = budgetService.findAllCategories(userDto.getUserId());
        System.out.println("allCategories = " + allCategories);
    }

    @Test
    void addCategory() {

        budgetService.addCategory(categoryDto);

        List<CategoryDTO> allCategories = budgetService.findAllCategories(6L);
        System.out.println("allCategories = " + allCategories);
    }

    @Test
    void updateCategoryName(){
        budgetService.addCategory(categoryDto);

        List<CategoryDTO> allCategories = budgetService.findAllCategories(6L);
        System.out.println("allCategories = " + allCategories);

        budgetService.updateCategoryName("test1", 6L, categoryDto.getCategoryId());
        List<CategoryDTO> newAllCategory = budgetService.findAllCategories(6L);
        System.out.println("newAllCategory = " + newAllCategory);


    }

    @Test
    void deleteCategory(){
        budgetService.addCategory(categoryDto);
        budgetService.deleteCategory(categoryDto.getUserId(), categoryDto.getCategoryId());
        List<CategoryDTO> allCategories = budgetService.findAllCategories(categoryDto.getUserId());
        System.out.println("allCategories = " + allCategories);
// 에러 발생
        //sed by: java.sql.SQLException: (conn=40) Lock wait timeout exceeded; try restarting transaction


    }

}