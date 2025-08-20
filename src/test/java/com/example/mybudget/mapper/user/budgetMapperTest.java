package com.example.mybudget.mapper.user;

import com.example.mybudget.dto.CategoryDTO;
import com.example.mybudget.dto.CategoryType;
import com.example.mybudget.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class budgetMapperTest {

    @Autowired
    private BudgetMapper budgetMapper;
    @Autowired private UserMapper userMapper;

    CategoryDTO categoryDTO;
    UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = UserDTO.builder()
                .loginId("test02")
                .password("test02")
                .userName("test02")
                .userBirthday("2000-01-01")
                .email("test02@gmail.com")
                .build();

        categoryDTO = CategoryDTO.builder()
                .categoryName("new user category")
                .categoryType(CategoryType.EXPENSE)
                .userId(6L)
                .build();
    }

    @Test
    void selectAllCategories() {
        List<CategoryDTO> categoryDTOS = budgetMapper.selectAllCategories(6L);
        System.out.println("categoryDTOS = " + categoryDTOS);
    }

    @Test
    void insertDefaultCategories() {
// 신규회원가입 시 기본 지출 카테고리 생성, 출력 테스트
        List<String> defaultCategories = List.of("식비","고정비","적금","기타");

        userMapper.insertUser(userDTO);
        System.out.println("회원가입 성공");
        budgetMapper.insertDefaultCategories(userDTO.getUserId(),defaultCategories);
        System.out.println("신규 회원 기본 카테고리 생성");

        List<CategoryDTO> categoryDTOS = budgetMapper.selectAllCategories(userDTO.getUserId());
        System.out.println("categoryDTOS.toString() = " + categoryDTOS.toString());
    }

    @Test
    void insertCategory() {
        budgetMapper.insertCategory(categoryDTO);
        System.out.println("사용자 지정 카테고리 추가 성공!");
        List<CategoryDTO> categoryDTOS = budgetMapper.selectAllCategories(6L);
        System.out.println("categoryDTOS = " + categoryDTOS.toString());

    }

    @Test
    void updateCategoryName() {
        List<CategoryDTO> categoryDTOS = budgetMapper.selectAllCategories(6L);
        System.out.println("categoryDTOS = " + categoryDTOS);
        budgetMapper.updateCategoryName("### new Name ",6L,10L);
        System.out.println("새로운 카테고리 이름 수정 완료 ");
        List<CategoryDTO> newCategoryDTOS = budgetMapper.selectAllCategories(6L);
        System.out.println("newCategoryDTOS = " + newCategoryDTOS);
    }

    @Test
    void softDeleteCategory() {

        budgetMapper.softDeleteCategory(10L, 6L);
        System.out.println("카테고리 삭제");

        List<CategoryDTO> categoryDTOS = budgetMapper.selectAllCategories(6L);
        System.out.println("categoryDTOS = " + categoryDTOS.toString());


    }
}