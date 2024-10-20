package com.zazu.ecommerce.Payment;

import com.zazu.ecommerce.Customer.CustomerResponse;
import com.zazu.ecommerce.Order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
