package com.example.mybudget.controller;

import com.example.mybudget.dto.BudgetListDTO;
import com.example.mybudget.dto.CategoryDTO;
import com.example.mybudget.dto.PaymentDTO;
import com.example.mybudget.service.BudgetService;
import com.example.mybudget.service.PaymentService;
import com.example.mybudget.service.PeriodService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expected")
public class BudgetController {
    private final BudgetService budgetService;
    private final PeriodService periodService;
    private final PaymentService paymentService;

    @GetMapping("/budget")
    public String budget(HttpSession session, Model model,
                         @RequestParam int year, @RequestParam int month) {
        if(session.getAttribute("userId") == null) {
            return "redirect:/user/login";
        }
        Long userId =(Long) session.getAttribute("userId");

        Long periodId = periodService.findPeriodId(year, month);

        budgetService.addBudgetList(userId,periodId);
        List<CategoryDTO> allCategories = budgetService.findAllCategories(userId);
        List<BudgetListDTO> allBudgets = budgetService.findAllBudgets(userId, periodId);
        int budgetAmountSum = budgetService.findBudgetAmountSum(userId, periodId);

        List<PaymentDTO> allPayment = paymentService.findAllPayment(userId);


        model.addAttribute("allCategories", allCategories);
        model.addAttribute("allBudgets", allBudgets);
        model.addAttribute("budgetAmountSum", budgetAmountSum);
        model.addAttribute("periodId", periodId);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("allPayment", allPayment);


        return "expectedBudget/category";
    }

    @PostMapping("/add/category")
    public String addCategory(CategoryDTO categoryDTO, Long periodId,
                              int year, int month,
                              HttpSession session) {
        budgetService.addCategory(categoryDTO,periodId,(Long)session.getAttribute("userId"));
        System.out.println("categoryDTO.toString() = " + categoryDTO.toString());

        return "redirect:/expected/budget?year=" + year + "&month=" + month;

    }


    @PostMapping("/add/paymentMethod")
    public String addPaymentMethod(HttpSession session,
                                   String paymentMethodName,
                                   int year, int month) {
        Long userId = (Long) session.getAttribute("userId");

        paymentService.addPayment(userId, paymentMethodName);

        return "redirect:/expected/budget?year=" + year + "&month=" + month;
    }



}
