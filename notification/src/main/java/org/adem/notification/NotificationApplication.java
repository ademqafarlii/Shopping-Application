package org.adem.notification;

import lombok.extern.slf4j.Slf4j;
import org.adem.notification.event.OrderPlacedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
    @KafkaListener(topics = "notificationTopic")
    public void handleNotificationForOrder(OrderPlacedEvent orderPlacedEvent){
        log.info("Received notification for order - {} ", orderPlacedEvent.getOrderNumber());
    }


}
