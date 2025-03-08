package com.roushan.ecommerce.mapper;

import com.roushan.ecommerce.dto.OrderLineRequest;
import com.roushan.ecommerce.dto.OrderLineResponse;
import com.roushan.ecommerce.model.Order;
import com.roushan.ecommerce.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = OrderLine.builder()
                .id(orderLineRequest.id())
                .quantity(orderLineRequest.quantity())
                .order(Order.builder()
                        .id(orderLineRequest.orderId())
                        .build())
                .productId(orderLineRequest.productId())
                .build();
        return orderLine;
    }


    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        OrderLineResponse orderLineResponse = new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity());
        return orderLineResponse;
    }
}
