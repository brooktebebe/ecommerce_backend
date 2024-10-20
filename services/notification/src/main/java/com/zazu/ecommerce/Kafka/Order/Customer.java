package com.zazu.ecommerce.Kafka.Order;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
