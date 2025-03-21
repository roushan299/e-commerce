package com.roushan.ecommerce.dto;

import com.roushan.ecommerce.model.Address;


public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
