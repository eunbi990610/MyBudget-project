package com.example.mybudget.api;

import com.example.mybudget.service.BudgetService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
                                       @RequestBody Long categoryId) {

        budgetService.deleteCategory((Long)session.getAttribute("userId"),categoryId);

        System.out.println("categoryId = " + categoryId +", userId = "+ (Long)session.getAttribute("userId"));

        return ResponseEntity.noContent().build();


    }

}
