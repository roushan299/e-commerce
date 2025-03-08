package com.roushan.ecommerce.service;


import com.roushan.ecommerce.dto.CustomerRequest;
import com.roushan.ecommerce.dto.CustomerResponse;
import com.roushan.ecommerce.exception.CustomerNotFoundException;
import com.roushan.ecommerce.mapper.CustomerMapper;
import com.roushan.ecommerce.model.Customer;
import com.roushan.ecommerce.repository.CustomerRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper mapper;


    public String createCustomer(CustomerRequest request) {
        Customer customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        Customer customer = repository.findById(request.id())
                .orElseThrow(()-> new CustomerNotFoundException(String.format("Cannot update:: NO customer found with the provided ID:: %s", request.id())));
        mergeCustomer(customer, request);
        repository.save(customer);

    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstname())){
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())){
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper:: fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean exitsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()-> new CustomerNotFoundException(String.format("NO customer found with the provided ID:: %s", customerId)));
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
