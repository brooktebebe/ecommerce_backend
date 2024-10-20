package com.zazu.ecommerce.Kafka;

import com.zazu.ecommerce.Product.PurchaseResponse;
import com.zazu.ecommerce.Customer.CustomerResponse;
import com.zazu.ecommerce.Order.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
