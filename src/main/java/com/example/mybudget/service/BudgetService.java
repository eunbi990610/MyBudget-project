package com.example.mybudget.service;

import com.example.mybudget.dto.CategoryDTO;
import com.example.mybudget.dto.CategoryType;
import com.example.mybudget.mapper.user.BudgetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetMapper budgetMapper;

    public List<CategoryDTO> findAllCategories(Long userId){
        return budgetMapper.selectAllCategories(userId);
    }

    public void addCategory(CategoryDTO categoryDto, Long userId){
        categoryDto.setUserId(userId);
        categoryDto.setCategoryType(CategoryType.EXPENSE);
        budgetMapper.insertCategory(categoryDto);
    }

    public void updateCategoryName(String categoryName, Long userId, Long categoryId){
        budgetMapper.updateCategoryName(categoryName, userId, categoryId);
    }

    public void deleteCategory(Long userId, Long categoryId){
        budgetMapper.softDeleteCategory(categoryId, userId);
    }




}
