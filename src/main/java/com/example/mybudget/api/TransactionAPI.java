package com.example.mybudget.api;

import com.example.mybudget.dto.TransactionDTO;
import com.example.mybudget.service.BudgetService;
import com.example.mybudget.service.TransactionService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction-api")
public class TransactionAPI {

    private final TransactionService transactionService;

    @GetMapping("/daily-sum/{firstOfMonth}")
    public List<Map<String, Object>> fillSumExpense(@PathVariable String firstOfMonth, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        System.out.println("firstOfMonth = " + firstOfMonth + ", userId = " + userId);

        return transactionService.findSumDailyExpense(userId, firstOfMonth);
    }

    @GetMapping("/daily/transaction-list/{transactionDate}")
    public List<TransactionDTO> findTransactionList(HttpSession session,
                                    @PathVariable String transactionDate) {

        Long userId = (Long) session.getAttribute("userId");

        return transactionService.findTransactionList(userId, transactionDate);
    }


}
