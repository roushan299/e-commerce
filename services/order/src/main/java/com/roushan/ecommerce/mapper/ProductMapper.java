package com.roushan.ecommerce.mapper;

import com.roushan.ecommerce.dto.OrderRequest;
import com.roushan.ecommerce.model.Order;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Order toOrder(OrderRequest request) {
        Order order = Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
        return order;
    }
}
