package com.roushan.ecommerce.constroller;

import com.roushan.ecommerce.dto.OrderLineResponse;
import com.roushan.ecommerce.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService service;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable("order-id") Integer orderId){
        List<OrderLineResponse> orderLineResponseList = service.findOrderLineByOrderId(orderId);
        return ResponseEntity.ok(orderLineResponseList);

    }


}
