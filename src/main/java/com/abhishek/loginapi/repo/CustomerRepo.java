package com.abhishek.loginapi.repo;

import com.abhishek.loginapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
