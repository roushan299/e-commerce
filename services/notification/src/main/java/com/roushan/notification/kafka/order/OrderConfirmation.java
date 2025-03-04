package com.roushan.notification.kafka.order;

import com.roushan.notification.dto.Customer;
import com.roushan.notification.dto.Products;
import com.roushan.notification.kafka.payment.PaymentMethod;
import org.apache.kafka.common.protocol.types.Field;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Products> products

) {
}
