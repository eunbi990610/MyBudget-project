package com.example.mybudget.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expected")
public class BudgetController {

    @GetMapping("/budget")
    public String budget() {
        return "expectedBudget/category";
    }

    @PostMapping("/add/category")
    public String addCategory() {
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
