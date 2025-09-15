package com.example.mybudget.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetDTO {

        private Long budgetId;
        private Long userId;
        private Long categoryId;
        private Long periodId;
//        private String categoryType;
//        private int budgetAmount;
        private String createAt;


    @Override
    public String toString() {
        return "BudgetDTO{" +
                "budgetId=" + budgetId +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", periodId=" + periodId +
//                ", categoryType='" + categoryType + '\'' +
//                ", budgetAmount=" + budgetAmount +
                ", createAt='" + createAt + '\'' +
                '}';
    }
}
