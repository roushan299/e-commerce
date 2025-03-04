package com.roushan.notification.dto;

import java.math.BigDecimal;

public record Products(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
