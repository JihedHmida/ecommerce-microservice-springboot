package com.jh.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerRequestDto customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid CustomerRequestDto customer) {
        return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return new ResponseEntity<>(customerService.findAllCustomers(), HttpStatus.OK);
    }


    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId) {
        return new ResponseEntity<>(customerService.findById(customerId), HttpStatus.OK);
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> delete(@PathVariable("customer-id") String customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
