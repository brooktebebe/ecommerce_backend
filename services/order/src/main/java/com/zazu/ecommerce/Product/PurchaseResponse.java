package com.zazu.ecommerce.Product;

import java.math.BigDecimal;

public record PurchaseResponse(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
