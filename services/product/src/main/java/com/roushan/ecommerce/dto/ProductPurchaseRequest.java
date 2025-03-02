package com.roushan.ecommerce.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer id,

        @NotNull(message = "Quantity is mandatory")
        double quantity
) {
}
