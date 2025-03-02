package com.roushan.ecommerce.service;

import com.roushan.ecommerce.customer.CustomerClient;
import com.roushan.ecommerce.dto.*;
import com.roushan.ecommerce.exception.BusinessException;
import com.roushan.ecommerce.kafka.OrderProducer;
import com.roushan.ecommerce.mapper.OrderMapper;
import com.roushan.ecommerce.mapper.ProductMapper;
import com.roushan.ecommerce.model.Order;
import com.roushan.ecommerce.product.ProductClient;
import com.roushan.ecommerce.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final ProductMapper productMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final OrderMapper orderMapper;


    public Integer createOrder(OrderRequest request) {
        //check customer --> open feign
        CustomerResponse customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exits with the provided id"+request.customerId()));


        //purchase the products --> product -ms (RestTemplate)
        List<PurchaseResponse> purchasedProducts = this.productClient.purchaseProducts(request.products());

        //persist order
        Order order = this.repository.save(productMapper.toOrder(request));

        //persist order lines
        for(PurchaseRequest purchaseRequest: request.products()){
            OrderLineRequest orderLineRequest = new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.id(),
                    purchaseRequest.quantity());
            orderLineService.saveOrderLine(orderLineRequest);
        }

        //TODO start payment process

        // send order confirmation --> notification -ms (kafka)
        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customer,
                purchasedProducts));

        return order.getId();

    }

    public List<OrderResponse> findAll() {
        List<Order> orderList = repository.findAll();
        List<OrderResponse> orderResponseList = orderList
                .stream()
                .map(orderMapper::toOrderResponse)
                .collect(Collectors.toList());
        return orderResponseList;
    }

    public OrderResponse findById(Integer orderId) {
        OrderResponse orderResponse = repository.findById(orderId)
                .map(orderMapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided Id: %d", orderId)));
        return orderResponse;
    }
}
