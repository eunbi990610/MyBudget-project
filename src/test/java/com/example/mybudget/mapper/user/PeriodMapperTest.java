package com.example.mybudget.mapper.user;

import com.example.mybudget.dto.PeriodAggregateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PeriodMapperTest {

    @Autowired
    private PeriodMapper periodMapper;

    PeriodAggregateDTO periodAggregateDTO;

    @BeforeEach
    void setUp() {
        periodAggregateDTO = PeriodAggregateDTO.builder()
                .userId(1L)
                .periodId(1L)
                .build();

    }

    @Test
    void insertOrUpdateAggregate() {

        periodMapper.insertAggregate(periodAggregateDTO);
        System.out.println(periodAggregateDTO.toString());

        periodAggregateDTO.setIncomeSum(300000);
        periodAggregateDTO.setPlannedExpenseSum(200000);
        System.out.println(periodAggregateDTO.toString());

        periodMapper.insertAggregate(periodAggregateDTO);
    }

    @Test
    void updateIncomeSum(){
        periodMapper.insertAggregate(periodAggregateDTO);
        periodMapper.updateIncomeSum(periodAggregateDTO.getUserId(), periodAggregateDTO.getPeriodId(), 100000);
        int availableAmount = periodMapper.selectAvailableAmount(periodAggregateDTO.getUserId(), periodAggregateDTO.getPeriodId());
        System.out.println("availableAmount = " + availableAmount);
    }


}