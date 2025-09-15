package com.example.mybudget.mapper.user;

import com.example.mybudget.dto.AggregateBudgetListDTO;
import com.example.mybudget.dto.BudgetRefDTO;
import com.example.mybudget.dto.PeriodAggregateDTO;
import com.example.mybudget.dto.PlannedExpenseSumDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PeriodMapper {

    Optional<Long> selectPeriodId(@Param("year") int year,
                                  @Param("month") int month);


    void insertPeriod(@Param("year") int year,
                      @Param("month") int month);


    void insertAggregate(@Param("userId") Long userId,
                         @Param("periodId")  Long periodId);
//
//
    boolean selectExistAggregateId(@Param("userId") Long userId,
                              @Param("periodId")  Long periodId);



    void insertAggregate(PeriodAggregateDTO periodAggregateDTO);


    void updatePlannedExpenseSum(BudgetRefDTO budgetRefDTO);



    void updateIncomeSum(@Param("userId") Long userId,
                         @Param("periodId") Long periodId,
                         @Param("additionalIncome") int additionalIncome);

    int selectAvailableAmount(@Param("userId") Long userId,
                              @Param("periodId") Long periodId);

    List<AggregateBudgetListDTO> selectAggregateList(@Param("userId") Long userId,
                                                     @Param("periodId") Long periodId);



}













