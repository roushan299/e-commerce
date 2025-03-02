package com.roushan.ecommerce.dto;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        double quantity
){ }
