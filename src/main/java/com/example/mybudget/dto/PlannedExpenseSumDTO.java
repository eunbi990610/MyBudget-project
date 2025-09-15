package com.example.mybudget.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlannedExpenseSumDTO {

        private Long userId;
        private Long periodId;
        private int oldBudgetAmount;
        private int newBudgetAmount;

    @Override
    public String toString() {
        return "PlannedExpenseSumDTO{" +
                "userId=" + userId +
                ", periodId=" + periodId +
                ", oldBudgetAmount=" + oldBudgetAmount +
                ", newBudgetAmount=" + newBudgetAmount +
                '}';
    }
}
