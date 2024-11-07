package com.abhishek.loginapi.mapper;

import com.abhishek.loginapi.dto.CustomerRequest;
import com.abhishek.loginapi.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(request.password())
                .build();
    }
}