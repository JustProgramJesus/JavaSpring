package com.example.orderservice.controller;

import com.example.orderservice.model.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderController(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public String placeOrder(@RequestBody OrderEvent order) {
        kafkaTemplate.send("order-topic", order);
        return "Order sent to Kafka!";
    }
}
