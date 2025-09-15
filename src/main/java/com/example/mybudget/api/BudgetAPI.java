package com.example.mybudget.api;

import com.example.mybudget.mapper.user.PeriodMapper;
import com.example.mybudget.service.BudgetService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/budget-api")
public class BudgetAPI {

    private final BudgetService budgetService;

    @DeleteMapping("/drop-category")
    public ResponseEntity<String> dropCategory(HttpSession session,
                                       @RequestBody Map<String, Long> body) {

        Long categoryId = body.get("categoryId");
        budgetService.deleteCategory((Long)session.getAttribute("userId"),categoryId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/modify-categoryName")
    public ResponseEntity<String> modifyCategoryName(HttpSession session,
                                                     @RequestBody Map<String, String> body) {

        Long userId = (Long)session.getAttribute("userId");
        Long categoryId = Long.parseLong(body.get("categoryId"));
        String newName = body.get("categoryName");
//        budgetService.
        if(budgetService.findDuplicateCategoryName(userId,newName)){
           return ResponseEntity.status(HttpStatus.CONFLICT).build(); //409
        }else{
            budgetService.updateCategoryName(newName,userId,categoryId);
            return ResponseEntity.noContent().build();//204
        }

    }


    @PatchMapping("/modify-budgetAmount")
    public void modifyBudgetAmount(@RequestBody Map<String, String> body) {
        Long budgetId = Long.parseLong(body.get("budgetId"));
        int newBudgetAmount = Integer.parseInt(body.get("newBudgetAmount"));
        System.out.println("budgetId = " + budgetId + ", newBudgetAmount = " + newBudgetAmount);
        try {
            budgetService.changeBudgetAmount(budgetId,newBudgetAmount);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        카테고리별 지출 예산을 수정/삭제하게 되면 period_aggregate의 합계가 변경되어야함
/*
          1. 카테고리 예산을 처음 등록할 때 insertPeriodAggregate-> userId, periodId 를 넣고 인스턴스 생성
          - 만약 aggregateId가 존재하면 insertPeriodAggregate 없이 금액 변경


 */

    }

}
