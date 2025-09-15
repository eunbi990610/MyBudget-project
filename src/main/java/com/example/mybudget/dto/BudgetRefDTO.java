package com.example.mybudget.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetRefDTO {
    private Long userId;
    private Long periodId;
    private Long budgetId;
    private int oldBudgetAmount;
    private int newBudgetAmount;

    @Override
    public String toString() {
        return "BudgetRefDTO{" +
                "userId=" + userId +
                ", periodId=" + periodId +
                ", oldBudgetAmount=" + oldBudgetAmount +
                ", newBudgetAmount=" + newBudgetAmount +
                '}';
    }
}
