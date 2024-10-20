package com.zazu.ecommerce.Dtos.Response;

import java.math.BigDecimal;

public record ProductResponse(
         Integer id,
         String name,
         String description,
         double availableQuantity,
         BigDecimal price,
         Integer productCategoryId,
         String productCategoryName,
         String productCategoryDescription
) {
}
