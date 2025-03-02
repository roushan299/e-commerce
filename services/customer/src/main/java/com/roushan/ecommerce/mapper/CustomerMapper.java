package com.roushan.ecommerce.mapper;

import com.roushan.ecommerce.dto.CustomerRequest;
import com.roushan.ecommerce.dto.CustomerResponse;
import com.roushan.ecommerce.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if(request == null){
            return Customer.builder().build();
        }
        Customer customer = Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
        return customer;
    }

    public CustomerResponse fromCustomer(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
        return customerResponse;
    }
}
