package com.ecommerce.inventory;

import com.ecommerce.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedEventConsumer {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consume(OrderCreatedEvent event) {
        log.info("Received OrderCreatedEvent: {}", event);
        try {
            inventoryService.deductStock(event.productId(), event.quantity());
        } catch (Exception e) {
            log.error("Error processing OrderCreatedEvent: {}", e.getMessage());
        }
    }
}
