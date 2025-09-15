package com.example.mybudget.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionDTO {

    private Long transactionId;  //AUTO
    private Long userId;  //
    private Long categoryId;  //O
    private Long paymentMethodId;  //O
    private Long periodId;  //
    private String transactionDate;  //O
    private int transactionAmount;  //O
    private CategoryType transactionType;  //O
    private String transactionTitle;  //O
    private String createAt;  //AUTO
    private String memo;  //O
    private String categoryName;
    private String paymentMethodName;



}
