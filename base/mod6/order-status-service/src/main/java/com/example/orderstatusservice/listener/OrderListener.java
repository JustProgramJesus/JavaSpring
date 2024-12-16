package com.example.orderstatusservice.listener;

import com.example.orderstatusservice.model.OrderStatusEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class OrderListener {

    private static final Logger log = LoggerFactory.getLogger(OrderListener.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderListener(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "order-topic", groupId = "order-status-group")
    public void listen(String message) {
        log.info("Received message: {}", message);
        try {
            OrderStatusEvent statusEvent = new OrderStatusEvent("CREATED", Instant.now());
            kafkaTemplate.send("order-status-topic", statusEvent);
            log.info("Sent status event: {}", statusEvent);
        } catch (Exception e) {
            log.error("Failed to process message: {}", message, e);
        }
    }
}