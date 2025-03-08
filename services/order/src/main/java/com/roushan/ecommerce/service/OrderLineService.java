package com.roushan.ecommerce.service;

import com.roushan.ecommerce.dto.OrderLineRequest;
import com.roushan.ecommerce.dto.OrderLineResponse;
import com.roushan.ecommerce.mapper.OrderLineMapper;
import com.roushan.ecommerce.model.OrderLine;
import com.roushan.ecommerce.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepository repository;

    @Autowired
    private OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = mapper.toOrderLine(orderLineRequest);
        return repository.save(orderLine).getId();
    }

    public List<OrderLineResponse> findOrderLineByOrderId(Integer orderId) {
        List<OrderLine> orderLineList = repository.findAllByOrderId(orderId);
        List<OrderLineResponse> orderLineResponseList = orderLineList
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
        return orderLineResponseList;
    }
}
