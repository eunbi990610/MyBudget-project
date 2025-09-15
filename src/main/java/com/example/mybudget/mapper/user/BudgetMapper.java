package com.example.mybudget.mapper.user;

import com.example.mybudget.dto.BudgetDTO;
import com.example.mybudget.dto.BudgetListDTO;
import com.example.mybudget.dto.BudgetRefDTO;
import com.example.mybudget.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BudgetMapper {

//    지출 카테고리
    List<CategoryDTO> selectAllCategories(Long userId);

    void insertDefaultCategories(@Param("userId") Long userId,
                                 @Param("categories") List<String> Categories);

    void insertCategory(CategoryDTO categoryDTO);

    void updateCategoryName(@Param("categoryName") String categoryName,
                            @Param("userId") Long userId,
                            @Param("categoryId") Long categoryId);

    void softDeleteCategory(@Param("categoryId") Long categoryId,
                            @Param("userId") Long userId);

    boolean checkCategoryName(@Param("userId") Long userId, @Param("categoryName") String newCategoryName);


//    예상 지출
    List<BudgetListDTO> selectAllBudgetList(@Param("userId") Long userId,
                                            @Param("periodId") Long periodId);

    void insertBudget(BudgetDTO budgetDTO);

    void insertBudgetList(@Param("userId") Long userId,
                          @Param("periodId") Long periodId);


    void updateBudgetAmount(@Param("budgetAmount") int budgetAmount,
                            @Param("budgetId") Long budgetId);

    void deleteBudget(@Param("userId") Long userId,
                      @Param("categoryId") Long categoryId);

    BudgetRefDTO selectBudgetRefByBudgetId(@Param("budgetId") Long budgetId);

    int selectBudgetAmountSum(@Param("userId") Long userId,
                              @Param("periodId") Long periodId);



}
