package com.roushan.ecommerce.mapper;

import com.roushan.ecommerce.dto.OrderResponse;
import com.roushan.ecommerce.model.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public OrderResponse toOrderResponse(Order order) {
            OrderResponse orderResponse = new OrderResponse(
                    order.getId(),
                    order.getReference(),
                    order.getTotalAmount(),
                    order.getPaymentMethod(),
                    order.getCustomerId()
            );
            return orderResponse;
    }
}
