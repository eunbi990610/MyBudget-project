package com.example.mybudget.service;

import com.example.mybudget.dto.*;
import com.example.mybudget.mapper.user.BudgetMapper;
import com.example.mybudget.mapper.user.PeriodMapper;
import com.example.mybudget.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PeriodService {

    private final PeriodMapper periodMapper;

    public Long findPeriodId(int year, int month){

        return periodMapper.selectPeriodId(year, month).orElseGet(()->{
            periodMapper.insertPeriod(year, month);
            return periodMapper.selectPeriodId(year, month)
                    .orElseThrow(()->new IllegalArgumentException("No period found for year " + year + " and month " + month));
        });


    }


    public boolean selectExistAggregateId(Long userId, Long periodId){
        return periodMapper.selectExistAggregateId(userId, periodId);
    }






}
