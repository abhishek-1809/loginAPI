package com.abhishek.loginapi.service;

import com.abhishek.loginapi.dto.CustomerRequest;
import com.abhishek.loginapi.dto.CustomerResponse;
import com.abhishek.loginapi.entity.Customer;
import com.abhishek.loginapi.mapper.CustomerMapper;
import com.abhishek.loginapi.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
}