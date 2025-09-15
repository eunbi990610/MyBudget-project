package com.example.mybudget.service;

import com.example.mybudget.dto.CategoryType;
import com.example.mybudget.dto.TransactionDTO;
import com.example.mybudget.mapper.TransactionMapper;
import com.example.mybudget.mapper.user.PeriodMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper transactionMapper;
    private final PeriodMapper periodMapper;



    public void addTransaction(TransactionDTO transactionDTO, int year, int month) {
//        transactionDate 를 추출해서 periodId의 값을 넣어야함
        Long periodId = periodMapper.selectPeriodId(year, month)
                .orElseThrow(() -> new IllegalArgumentException("periodId not found"));

        transactionDTO.setPeriodId(periodId);

        System.out.println("transactionDTO = " + transactionDTO.toString());
        transactionMapper.insertTransaction(transactionDTO);

    }

    public List<Map<String, Object>> findSumDailyExpense(Long userId,String startOfMonth) {
        return transactionMapper.selectSumDailyExpense(userId, startOfMonth);
    }

    public List<TransactionDTO> findTransactionList(Long userId, String transactionDate){
        return transactionMapper.selectTransactionList(userId, transactionDate);
    }

    public int findTotalMonthlyAmountByTransactionType(Long userId, Long periodId, CategoryType categoryType){
        return transactionMapper.selectTotalMonthlyAmount(userId, periodId, categoryType);
    }


}
