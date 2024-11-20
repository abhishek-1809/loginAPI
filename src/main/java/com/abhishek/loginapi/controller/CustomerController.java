package com.abhishek.loginapi.controller;

import com.abhishek.loginapi.dto.CustomerRequest;
import com.abhishek.loginapi.dto.CustomerResponse;
import com.abhishek.loginapi.dto.LoginRequest;
import com.abhishek.loginapi.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        System.out.println("testEndpoint ok");
        return ResponseEntity.ok("Test endpoint ok");
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        CustomerResponse response = customerService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
        String response = customerService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String email) {
        CustomerResponse response = customerService.retrieveCustomer(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
