package com.example.mybudget.mapper.user;

import com.example.mybudget.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BudgetMapper {

    List<CategoryDTO> selectAllCategories(Long userId);

    void insertDefaultCategories(@Param("userId") Long userId,
                                 @Param("categories") List<String> Categories);

    void insertCategory(CategoryDTO categoryDTO);

    void updateCategoryName(@Param("categoryName") String categoryName,
                            @Param("userId") Long userId,
                            @Param("categoryId") Long categoryId);

    void softDeleteCategory(@Param("categoryId") Long categoryId,
                            @Param("userId") Long userId);


}
