package com.abhishek.loginapi.mapper;

import com.abhishek.loginapi.dto.CustomerRequest;
import com.abhishek.loginapi.dto.CustomerResponse;
import com.abhishek.loginapi.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setPassword(request.password());
        return customer;
    }

    public CustomerResponse toDto(Customer customer) {
        return new CustomerResponse(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
                );
    }
}