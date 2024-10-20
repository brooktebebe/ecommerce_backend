package com.zazu.ecommerce.Notification;

import com.zazu.ecommerce.Kafka.Payment.PaymentConfirmation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification,String> {
}
