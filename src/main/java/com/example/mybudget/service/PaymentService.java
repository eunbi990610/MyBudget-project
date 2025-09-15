package com.example.mybudget.service;

import com.example.mybudget.dto.PaymentDTO;
import com.example.mybudget.mapper.PaymentMethodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMethodMapper  paymentMethodMapper;

    public void addPayment(Long userId, String paymentMethodName){
        paymentMethodMapper.insertPayment(userId, paymentMethodName);
    }

    public List<PaymentDTO> findAllPayment(Long userId){
        return paymentMethodMapper.selectAllPayment(userId);
    }



}
