package com.roushan.ecommerce.dto;

import com.roushan.ecommerce.model.PaymentMethod;
import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {
}
