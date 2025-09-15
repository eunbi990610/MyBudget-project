package com.example.mybudget.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaymentDTO {

    private Long paymentMethodId;
    private Long userId;
    private String paymentMethodName;
    private String createAt;

}
