package com.abhishek.loginapi.service;

import com.abhishek.loginapi.dto.CustomerRequest;
import com.abhishek.loginapi.dto.CustomerResponse;
import com.abhishek.loginapi.dto.LoginRequest;
import com.abhishek.loginapi.entity.Customer;
import com.abhishek.loginapi.exception.CustomerNotFoundException;
import com.abhishek.loginapi.helper.EncryptionService;
import com.abhishek.loginapi.helper.JWTHelper;
import com.abhishek.loginapi.mapper.CustomerMapper;
import com.abhishek.loginapi.repo.CustomerRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import static java.lang.String.format;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo repo;

    @Autowired
    CustomerMapper mapper;

    @Autowired
    EncryptionService encryptionService;

    @Autowired
    JWTHelper jwtHelper;

    @Autowired
    AuthenticationManager authManager;

    public CustomerResponse register(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        Customer savedCustomer = repo.save(customer);
        return mapper.toDto(savedCustomer);
    }

    public String login(LoginRequest request) {
        Customer customer = repo.findByEmail(request.email());
        if (customer == null) {
            throw new CustomerNotFoundException(format("Customer with email %s not found", request.email()));
        }
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        if(auth.isAuthenticated())
            return jwtHelper.generateToken(request.email());

        return "Wrong Username or Password";
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = repo.findByEmail(email);
        if(customer == null) {
            throw new CustomerNotFoundException(format("Customer with email %s not found", email));
        }
        return mapper.toDto(customer);
    }


}