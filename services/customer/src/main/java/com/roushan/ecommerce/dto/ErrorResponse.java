package com.roushan.ecommerce.dto;

import java.util.HashMap;


public record ErrorResponse(
        HashMap<String, String> errors
) {
}
