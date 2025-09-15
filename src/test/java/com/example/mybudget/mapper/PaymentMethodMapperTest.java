package com.example.mybudget.mapper;

import com.example.mybudget.dto.PaymentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PaymentMethodMapperTest {

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    PaymentDTO paymentDTO;

    @BeforeEach
    void setUp() {
        paymentDTO = paymentDTO.builder()
                .paymentMethodName("test")
                .userId(1L)
                .build();
    }

    @Test
    void insertAndSeelctPayment() {
        paymentMethodMapper.insertPayment(paymentDTO.getUserId(),  paymentDTO.getPaymentMethodName());
        List<PaymentDTO> paymentDTOS = paymentMethodMapper.selectAllPayment(paymentDTO.getUserId());

        System.out.println(paymentDTOS.toString());
    }


}