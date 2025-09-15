package com.example.mybudget.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AggregateBudgetListDTO {

    private Long aggregateId;
    private Long userId;
    private Long periodId;
    private int incomeSum;
    private int plannedExpenseSum;
    private int actualExpenseSum;
    private int availableSum;
    private String updateAt;

    @Override
    public String toString() {
        return "AggregateBudgetListDTO{" +
                "aggregateId=" + aggregateId +
                ", userId=" + userId +
                ", periodId=" + periodId +
                ", incomeSum=" + incomeSum +
                ", plannedExpenseSum=" + plannedExpenseSum +
                ", actualExpenseSum=" + actualExpenseSum +
                ", availableSum=" + availableSum +
                ", updateAt='" + updateAt + '\'' +
                '}';
    }
}
