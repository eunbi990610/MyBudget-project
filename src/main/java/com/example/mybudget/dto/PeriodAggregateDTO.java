package com.example.mybudget.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PeriodAggregateDTO {

    private Long userId;
    private Long periodId;
    private int incomeSum;
    private int plannedExpenseSum;
    private int availableAmount;


}
