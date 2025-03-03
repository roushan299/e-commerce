package com.roushan.ecommerce.service;

import com.roushan.ecommerce.dto.PaymentNotificationRequest;
import com.roushan.ecommerce.dto.PaymentRequest;
import com.roushan.ecommerce.mapper.paymentMapper;
import com.roushan.ecommerce.model.Payment;
import com.roushan.ecommerce.notification.NotificationProducer;
import com.roushan.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final paymentMapper mapper;
    private NotificationProducer notificationProducer;


    public Integer createPayment(PaymentRequest request) {
        Payment payment = repository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(new PaymentNotificationRequest(
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstname(),
                request.customer().lastname(),
                request.customer().email()));
        return payment.getId();
    }
}
