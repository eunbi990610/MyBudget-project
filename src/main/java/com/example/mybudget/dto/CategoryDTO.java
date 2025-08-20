package com.example.mybudget.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private Long userId;
    private Long categoryId;
    private String categoryName;
    private CategoryType categoryType;
    private String createAt;

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "userId=" + userId +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryType=" + categoryType +
                ", createAt='" + createAt + '\'' +
                '}';
    }
}
