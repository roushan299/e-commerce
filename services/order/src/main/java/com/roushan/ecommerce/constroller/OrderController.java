package com.roushan.ecommerce.constroller;

import com.roushan.ecommerce.dto.OrderRequest;
import com.roushan.ecommerce.dto.OrderResponse;
import com.roushan.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createdOrder(@RequestBody @Valid OrderRequest request){
        return ResponseEntity.ok((service.createOrder(request)));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        List<OrderResponse> orderResponseList = service.findAll();
        return ResponseEntity.ok(orderResponseList);
    }

    @GetMapping("{order-id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") Integer orderId){
        OrderResponse orderResponse = service.findById(orderId);
        return ResponseEntity.ok(orderResponse);
    }

}
