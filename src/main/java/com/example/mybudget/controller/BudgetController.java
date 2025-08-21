package com.example.mybudget.controller;

import com.example.mybudget.dto.CategoryDTO;
import com.example.mybudget.service.BudgetService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expected")
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping("/budget")
    public String budget(HttpSession session, Model model) {
        if(session.getAttribute("userId") == null) {
            return "redirect:/user/login";
        }


        Long userId =(Long) session.getAttribute("userId");

        List<CategoryDTO> allCategories = budgetService.findAllCategories(userId);

        model.addAttribute("allCategories", allCategories);

        return "expectedBudget/category";
    }

    @PostMapping("/add/category")
    public String addCategory(CategoryDTO categoryDTO,
                              HttpSession session) {
        budgetService.addCategory(categoryDTO, (Long)session.getAttribute("userId"));
        System.out.println("categoryDTO.toString() = " + categoryDTO.toString());


        return "redirect:/expected/budget";
    }

    @PostMapping("/add/income")
    public String addIncome(){
        return "redirect:/expected/budget";
    }

    @PostMapping("/add/expense")
    public String addExpense(){
        return "redirect:/expected/budget";
    }




}
