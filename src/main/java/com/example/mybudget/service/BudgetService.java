package com.example.mybudget.service;

import com.example.mybudget.dto.*;
import com.example.mybudget.mapper.user.BudgetMapper;
import com.example.mybudget.mapper.user.PeriodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetMapper budgetMapper;
    private final PeriodMapper periodMapper;

    public List<CategoryDTO> findAllCategories(Long userId){
        return budgetMapper.selectAllCategories(userId);
    }

    public void addCategory(CategoryDTO categoryDto,Long periodId, Long userId){
        categoryDto.setUserId(userId);
        categoryDto.setCategoryType(CategoryType.EXPENSE);
        budgetMapper.insertCategory(categoryDto);

        BudgetDTO budgetDTO = BudgetDTO.builder()
                .userId(userId)
                .categoryId(categoryDto.getCategoryId())
                .periodId(periodId)
                .build();

        budgetMapper.insertBudget(budgetDTO);
    }

    public void updateCategoryName(String categoryName, Long userId, Long categoryId){
        budgetMapper.updateCategoryName(categoryName, userId, categoryId);
    }

    public void deleteCategory(Long userId, Long categoryId){
        budgetMapper.softDeleteCategory(categoryId, userId);
//      해당 예산 budget도 삭제
        budgetMapper.deleteBudget(userId, categoryId);
    }

//    예상 지출 예산

    public List<BudgetListDTO> findAllBudgets(Long userId, Long periodId){
        return budgetMapper.selectAllBudgetList(userId,periodId);
    }

    public void addBudgetList(Long userId, Long periodId){
        budgetMapper.insertBudgetList(userId,periodId);
    }

    public boolean findDuplicateCategoryName(Long userId, String newCategoryName){
        return budgetMapper.checkCategoryName(userId,newCategoryName);
    }

    public void changeBudgetAmount(Long budgetId, int newBudgetAmount){

        BudgetRefDTO budgetRefDTO = budgetMapper.selectBudgetRefByBudgetId(budgetId);
        budgetRefDTO.setNewBudgetAmount(newBudgetAmount);

        System.out.println("budgetRefDTO.toString() = " + budgetRefDTO.toString());

        // 카테고리별 예상예산을 등록/수정
        budgetMapper.updateBudgetAmount(newBudgetAmount, budgetId);
        // 변동된 금액에 대해서 종합예산 테이블에 반영해야함

        periodMapper.updatePlannedExpenseSum(budgetRefDTO);



    }

//    public void deleteBudget(Long budgetId){
//        budgetMapper.deleteBudget(budgetId);
//    }

    public List<AggregateBudgetListDTO> findAggregateList(Long userId, Long periodId){
        return periodMapper.selectAggregateList(userId, periodId);
    }

    public int findBudgetAmountSum(Long userId, Long periodId){
        return budgetMapper.selectBudgetAmountSum(userId, periodId);
    }

}
