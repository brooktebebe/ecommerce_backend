package com.zazu.ecommerce.Dtos.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is required.")
        Integer productId,
        @Positive(message = "Quantity is mandatory")
        double quantity
) {
}
