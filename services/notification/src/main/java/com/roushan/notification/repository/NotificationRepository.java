package com.roushan.notification.repository;

import com.roushan.notification.kafka.payment.PaymentConfirmation;
import com.roushan.notification.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
}
