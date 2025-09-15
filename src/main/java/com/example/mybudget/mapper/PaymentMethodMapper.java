package com.example.mybudget.mapper;

import com.example.mybudget.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaymentMethodMapper {
   void insertPayment(@Param("userId") Long userId,
                      @Param("paymentMethodName") String paymentMethodName);

   List<PaymentDTO> selectAllPayment(@Param("userId") Long userId);

}













