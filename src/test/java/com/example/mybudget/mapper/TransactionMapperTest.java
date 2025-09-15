package com.example.mybudget.mapper;

import com.example.mybudget.dto.CategoryType;
import com.example.mybudget.dto.TransactionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TransactionMapperTest {

    TransactionDTO transactionDTO;
    @Autowired
    TransactionMapper transactionMapper;


    @BeforeEach
    void setUp() {
        transactionDTO = TransactionDTO.builder()
                .categoryId(111L)
                .periodId(10L)
                .transactionAmount(100000)
                .transactionDate("2025-9-9")
                .transactionTitle("Test Transaction")
                .transactionType(CategoryType.EXPENSE)
                .userId(1L)
                .paymentMethodId(1L)
                .memo("Test memo")
                .build();
    }

    @Test
    void insertAndSelectTransaction() {
        transactionMapper.insertTransaction(transactionDTO);
        List<TransactionDTO> transactionDTOS = transactionMapper.selectAllTransactions(1L,10L);
        System.out.println("transactionDTOS = " + transactionDTOS);
        System.out.println("id = "+ transactionDTO.getTransactionId());

        transactionMapper.deleteTransaction(9L);
        List<TransactionDTO> afterTransactionDTOS = transactionMapper.selectAllTransactions(1L, 10L);
        System.out.println("afterTransactionDTOS = " + afterTransactionDTOS);

    }

    @Test
    void selectSumDailyExpense(){
        List<Map<String, Object>> maps = transactionMapper.selectSumDailyExpense(1L, "2025-9-1");
        maps.forEach(map -> {
            System.out.println("map = " + map);
        });
    }

    @Test
    void selectSumDaily(){
        List<TransactionDTO> transactionDTOS = transactionMapper.selectTransactionList(1L, "2025-09-03");
        transactionDTOS.forEach(transactionDTO -> {
            System.out.println("transactionDTO = " + transactionDTO);
        });
    }

    @Test
    void selectSumMonthlyExpense(){
        int totalExpenseAmount = transactionMapper.selectTotalMonthlyAmount(1L, 10L, CategoryType.EXPENSE);
        System.out.println("totalAmount = " + totalExpenseAmount);
        int totalIncomeAmount = transactionMapper.selectTotalMonthlyAmount(1L, 10L, CategoryType.INCOME);
        System.out.println("totalIncomeAmount = " + totalIncomeAmount);
    }

}