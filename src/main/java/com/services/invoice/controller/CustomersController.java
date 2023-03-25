package com.services.invoice.controller;

import com.services.invoice.entity.Customer;
import com.services.invoice.repository.CustomerRepo;
import com.services.invoice.validator.CustomerRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomersController {

    CustomerRepo customerRepo;

    public CustomersController(CustomerRepo customerRepo) {

        this.customerRepo = customerRepo;
    }

    @PostMapping("/api/v1/customers")
    public ResponseEntity<Customer> store(@Valid @RequestBody CustomerRequest customer) {

        return new ResponseEntity<>(
                this.customerRepo.save(customer.toCustomer()),
                HttpStatus.OK);
    }
}
