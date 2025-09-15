package com.example.mybudget.service;

import com.example.mybudget.dto.*;
import com.example.mybudget.mapper.user.PeriodMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class BudgetServiceTest {

    UserDTO userDto;
    CategoryDTO categoryDto;

    @Autowired
    UserService userService;
    @Autowired
    BudgetService budgetService;
    @Autowired
    private PeriodMapper periodMapper;

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
//                .userId(6L)
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

        budgetService.addCategory(categoryDto,1L, 6L);

        List<CategoryDTO> allCategories = budgetService.findAllCategories(6L);
        System.out.println("allCategories = " + allCategories);
    }

    @Test
    void updateCategoryName(){
        budgetService.addCategory(categoryDto, 1L,6L);

        List<CategoryDTO> allCategories = budgetService.findAllCategories(6L);
        System.out.println("allCategories = " + allCategories);

        budgetService.updateCategoryName("test1", 6L, categoryDto.getCategoryId());
        List<CategoryDTO> newAllCategory = budgetService.findAllCategories(6L);
        System.out.println("newAllCategory = " + newAllCategory);


    }

    @Test
    void deleteCategory(){
        budgetService.addCategory(categoryDto,1L, 6L);
        List<CategoryDTO> allCategories = budgetService.findAllCategories(categoryDto.getUserId());
        System.out.println("allCategories = " + allCategories);
        budgetService.deleteCategory(categoryDto.getUserId(), categoryDto.getCategoryId());
        List<CategoryDTO> afterCategories = budgetService.findAllCategories(categoryDto.getUserId());
        System.out.println("afterCategories = " + afterCategories);
// 에러 발생
        //sed by: java.sql.SQLException: (conn=40) Lock wait timeout exceeded; try restarting transaction


    }

    @Test
    void changeCategoryName(){
        List<BudgetListDTO> allBudgets = budgetService.findAllBudgets(1L, 1L);
        System.out.println("allBudgets = " + allBudgets.toString());
        budgetService.changeBudgetAmount(6L,1000000);
        List<BudgetListDTO> afterBudgets = budgetService.findAllBudgets(1L, 1L);
        System.out.println("afterBudgets = " + afterBudgets);
    }

    @Test
    void deleteBudget(){
        List<BudgetListDTO> allBudgets = budgetService.findAllBudgets(1L, 1L);
        System.out.println("allBudgets = " + allBudgets.toString());
//        budgetService.deleteBudget(6L);
        List<BudgetListDTO> afterBudgets = budgetService.findAllBudgets(1L, 1L);
        System.out.println("afterBudgets = " + afterBudgets);
    }

    @Test
    void changeBudgetAmount(){

        budgetService.changeBudgetAmount(6L,1000000);

        List<BudgetListDTO> allBudgets = budgetService.findAllBudgets(1L, 1L);
        System.out.println("allBudgets = " + allBudgets.toString());

        List<AggregateBudgetListDTO> aggregateBudgetListDTOS = periodMapper.selectAggregateList(1L, 1L);
        System.out.println("aggregateBudgetListDTOS = " + aggregateBudgetListDTOS.toString());

    }


}