package com.zazu.ecommerce.Customer;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
