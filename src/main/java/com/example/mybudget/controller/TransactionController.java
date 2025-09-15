package com.example.mybudget.controller;


import com.example.mybudget.dto.CategoryDTO;
import com.example.mybudget.dto.PaymentDTO;
import com.example.mybudget.dto.TransactionDTO;
import com.example.mybudget.service.BudgetService;
import com.example.mybudget.service.PaymentService;
import com.example.mybudget.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final BudgetService budgetService;
    private final PaymentService paymentService;
    private final TransactionService transactionService;

    @GetMapping("/daily")
    public String dailyTransactions(Model model, HttpSession session,
                                    @RequestParam int year, @RequestParam int month) {

        Long userId = (Long)session.getAttribute("userId");
        if(userId == null){
            return "redirect:/user/login";
        }

        List<CategoryDTO> allCategories = budgetService.findAllCategories(userId);
        List<PaymentDTO> allPayment = paymentService.findAllPayment(userId);

        model.addAttribute("allCategories", allCategories);
        model.addAttribute("allPayment", allPayment);
        model.addAttribute("year", year);
        model.addAttribute("month", month);


        return "transactions/daily";
    }

    @PostMapping("/add")
    public String addTransaction(HttpSession session, TransactionDTO transactionDTO) {

        int year = Integer.parseInt(transactionDTO.getTransactionDate().substring(0,4));
        int month = Integer.parseInt(transactionDTO.getTransactionDate().substring(5,7));

        Long userId = (Long) session.getAttribute("userId");
        transactionDTO.setUserId(userId);

        transactionService.addTransaction(transactionDTO, year, month);


        return "redirect:/transactions/daily?year=" + year + "&month=" + month;
    }




}
