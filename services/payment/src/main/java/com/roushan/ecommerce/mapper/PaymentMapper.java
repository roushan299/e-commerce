package com.roushan.ecommerce.mapper;

import com.roushan.ecommerce.dto.PaymentRequest;
import com.roushan.ecommerce.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        Payment payment = Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .build();
        return payment;

    }
}
