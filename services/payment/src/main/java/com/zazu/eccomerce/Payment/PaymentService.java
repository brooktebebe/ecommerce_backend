package com.zazu.eccomerce.Payment;

import com.zazu.eccomerce.Notification.NotificationProducer;
import com.zazu.eccomerce.Notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  PaymentService {
    private  final PaymentRepository paymentRepository;
            private final PaymentMapper paymentMapper;
            private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        var payment=paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        notificationProducer.sendNotification(new PaymentNotificationRequest(
                paymentRequest.orderReference(),
                paymentRequest.amount(),
                paymentRequest.paymentMethod(),
                paymentRequest.customer().firstName(),
                paymentRequest.customer().lastName(),
                paymentRequest.customer().email()
        ));
        return payment.getId();

    }
}
