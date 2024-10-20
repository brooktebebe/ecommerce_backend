package com.zazu.ecommerce.Order;

import com.zazu.ecommerce.Customer.CustomerClient;
import com.zazu.ecommerce.OrderLine.OrderLineService;
import com.zazu.ecommerce.Payment.PaymentClient;
import com.zazu.ecommerce.Payment.PaymentRequest;
import com.zazu.ecommerce.Product.ProductClient;
import com.zazu.ecommerce.OrderLine.OrderLineRequest;
import com.zazu.ecommerce.Product.PurchaseRequest;
import com.zazu.ecommerce.Exception.BusinessException;
import com.zazu.ecommerce.Kafka.OrderConfirmation;
import com.zazu.ecommerce.Kafka.OrderProducer;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;
    public List<OrderResponse> findAll(){
    return  orderRepository.findAll().stream().map(orderMapper::fromOrder).collect(Collectors.toList());
    }

    public Integer createOrder(OrderRequest orderRequest) {
        //check if the customer exists or not. //used openfeign method one
        var customer = customerClient.findCustomerById(orderRequest.customerId()).orElseThrow(()-> new BusinessException("Can't create order. no customer exist with this ID "+orderRequest.customerId()));
        //purchase the product.->product microservice //using rest template method two
       var purchasedProducts= productClient.purchaseProducts(orderRequest.products());

        //persist the order
        var order= orderRepository.save(orderMapper.toOrder(orderRequest));
        //persist the order line
        for(PurchaseRequest purchaseRequest:orderRequest.products()){
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));
        }
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                orderRequest.id(),
                orderRequest.reference(),
                customer
        );
        //  start payment process
        paymentClient.requestOrderPayment(paymentRequest);
        //send the order confirmation -> use kafka
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId).map(orderMapper::fromOrder).orElseThrow(()->new EntityNotFoundException(String.format(
                "No order found with the provided ID %d",orderId
        )));
    }
}
