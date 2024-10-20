package com.zazu.ecommerce.Dto.Request;

import com.zazu.ecommerce.Entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname is required.")
        String firstName,
        @NotNull(message = "Customer lastName is required.")
        String lastName,
        @NotNull(message = "Customer email is required.")
        @Email(message = "Customer email is not a valid email.")
        String email,
        Address address
) {

}
