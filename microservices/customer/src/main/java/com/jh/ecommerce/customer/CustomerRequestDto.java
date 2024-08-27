package com.jh.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDto(

        String id,

        @NotNull(message = "Customer first name is required")
        String firstName,
        @NotNull(message = "Customer last name is required")
        String lastName,
        @Email(message = "Not valid email")
        @NotNull(message = "Customer Email name is required")
        String email,
        @NotNull(message = "Customer first name is required")

        Address address
) {
}
