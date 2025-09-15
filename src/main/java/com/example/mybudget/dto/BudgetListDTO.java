package com.example.mybudget.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetListDTO {
private Long budgetId;
private Long userId;
private Long categoryId;
private Long periodId;
private String categoryType;
private String categoryName;
private int budgetAmount;

    @Override
    public String toString() {
        return "BudgetListDTO{" +
                "budgetId=" + budgetId +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", periodId=" + periodId +
                ", categoryType='" + categoryType + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", budgetAmount=" + budgetAmount +
                '}';
    }
}
