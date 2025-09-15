package com.example.mybudget.service;

import com.example.mybudget.dto.PaymentDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;


    @Test
    void findAllPayment(){
        paymentService.addPayment(1L,"test");
        List<PaymentDTO> allPayment = paymentService.findAllPayment(1L);
        Assertions.assertThat(allPayment.size()).isEqualTo(3);
//        System.out.println("allPayment = " + allPayment.toString());
    }


}