package com.example.mybudget.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    TransactionService transactionService;

    @Test
    void findSumDailyExpense(){
        List<Map<String, Object>> sumDailyExpense = transactionService.findSumDailyExpense(1L, "2025-10-01");
        Assertions.assertThat(sumDailyExpense.size()).isEqualTo(31);
    }

//    @Test
//    void findSumDailyExpenseFailedTest(){
//        List<Map<String, Object>> sumDailyExpense = transactionService.findSumDailyExpense(1L, "2025-10-01");
//        Assertions.assertThat(sumDailyExpense.size()).isEqualTo(30);
//    }


}