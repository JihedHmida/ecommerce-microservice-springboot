package com.jh.ecommerce.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequestDto request) {
        if (request == null) {
            return null;
        }
        return Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }


    public CustomerResponse fromCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getLastName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }

}
