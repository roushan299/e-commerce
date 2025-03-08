package com.roushan.ecommerce.service;

import com.roushan.ecommerce.dto.PaymentNotificationRequest;
import com.roushan.ecommerce.dto.PaymentRequest;
import com.roushan.ecommerce.mapper.PaymentMapper;
import com.roushan.ecommerce.model.Payment;
import com.roushan.ecommerce.notification.NotificationProducer;
import com.roushan.ecommerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private PaymentMapper mapper;

    @Autowired
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
