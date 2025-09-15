package com.example.mybudget.mapper.user;

import com.example.mybudget.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class BudgetMapperTest2 {

    @Autowired
    private BudgetMapper budgetMapper;

    @Autowired
            private PeriodMapper periodMapper;

    BudgetDTO budgetDTO;
    UserDTO userDTO;
    CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {

//        userDTO = UserDTO.builder()
//                .loginId("test")
//                .userBirthday("1999-06-29")
//                .userName("user1")
//                .password("password1")
//                .email("email1@gmail.com")
//                .build();

        categoryDTO = CategoryDTO.builder()
                .categoryName("new user category")
                .categoryType(CategoryType.EXPENSE)
                .userId(1L)
                .build();

        budgetDTO = BudgetDTO.builder()
                .periodId(1L)
                .userId(1L)
                .build();
    }

    @Test
    void selectAllBudgetList() {
        List<BudgetListDTO> budgetListDTOS = budgetMapper.selectAllBudgetList(1L, 1L);
        budgetListDTOS.forEach(dto -> {
            System.out.println(dto.toString());
        });
    }

    @Test
    void insertBudget() {

        budgetMapper.insertCategory(categoryDTO);
        System.out.println("@@categoryId : "+categoryDTO.getCategoryId());
        budgetDTO.setCategoryId(categoryDTO.getCategoryId());
        budgetMapper.insertBudget(budgetDTO);

    }

    @Test
    void updateBudgetAmount() {
        budgetMapper.updateBudgetAmount(10000,6L);
    }

    @Test
    void deleteBudget() {

//        budgetMapper.deleteBudget(6L);
    }

//    @Test
//    void changeBudgetAmount(){
//
//        BudgetRefDTO budgetRefDTO = budgetMapper.selectBudgetRefByBudgetId(6L);
//        budgetRefDTO.setNewBudgetAmount(100000);
//
//        System.out.println("budgetRefDTO = " + budgetRefDTO.toString());
//
//        budgetMapper.updateBudgetAmount(budgetRefDTO.getNewBudgetAmount(), 6L);
//        List<BudgetListDTO> budgetListDTOS = budgetMapper.selectAllBudgetList(1L, 1L);
//        System.out.println("budgetListDTOS = " + budgetListDTOS.toString());
//
//
//        periodMapper.updatePlannedExpenseSum(budgetRefDTO);
//        List<AggregateBudgetListDTO> aggregateBudgetListDTOS = periodMapper.selectAggregateList(1L, 1L);
//        System.out.println("aggregateBudgetListDTOS = " + aggregateBudgetListDTOS.toString());
//
//
//        System.out.println("두번째 수정");
////        BudgetRefDTO budgetRefDTO2 = budgetMapper.selectUserIdAndPeriodIdByBudgetId(6L);
//        budgetRefDTO.setNewBudgetAmount(150000);
//
//
//
//        budgetMapper.updateBudgetAmount(budgetRefDTO.getNewBudgetAmount(), 6L);
//        List<BudgetListDTO> budgetListDTOS2 = budgetMapper.selectAllBudgetList(1L, 1L);
//        System.out.println("@@budgetListDTOS2 = " + budgetListDTOS2.toString());
//
//
//        periodMapper.updatePlannedExpenseSum(budgetRefDTO);
//        List<AggregateBudgetListDTO> aggregateBudgetListDTOS2 = periodMapper.selectAggregateList(1L, 1L);
//        System.out.println("@@aggregateBudgetListDTOS2 = " + aggregateBudgetListDTOS2.toString());
//
//
//
//    }


    @Test
    void selectBudgetByBudgetId() {
        Long budgetId = 6L;
        BudgetRefDTO budgetRefDTO = budgetMapper.selectBudgetRefByBudgetId(budgetId);
        System.out.println("budgetRefDTO = " + budgetRefDTO);
    }

    @Test
    void selectBudgetAmountSum() {
        budgetMapper.selectBudgetAmountSum(6L, 1L);
    }


}