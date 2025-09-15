package com.example.mybudget.mapper;

import com.example.mybudget.dto.CategoryType;
import com.example.mybudget.dto.PaymentDTO;
import com.example.mybudget.dto.TransactionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TransactionMapper {

   void insertTransaction(TransactionDTO transactionDTO);

   List<TransactionDTO> selectAllTransactions(@Param("userId") Long userId,
                                              @Param("periodId") Long periodId);

   void deleteTransaction(@Param("transactionId") Long transactionId);

   List<Map<String, Object>> selectSumDailyExpense(@Param("userId") Long userId,
                                                   @Param("startOfMonth") String startOfMonth);

   List<TransactionDTO> selectTransactionList(@Param("userId") Long userId,
                                              @Param("transactionDate") String transactionDate);

   int selectTotalMonthlyAmount(@Param("userId") Long userId,
                                @Param("periodId") Long periodId,
                                @Param("transactionType") CategoryType transactionType);


}













