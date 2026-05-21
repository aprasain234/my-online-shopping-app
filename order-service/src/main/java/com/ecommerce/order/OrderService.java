package com.ecommerce.order;

import com.ecommerce.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Transactional
    public Order createOrder(OrderRequest orderRequest) {
        log.info("Creating order for product {}", orderRequest.productId());

        Order order = Order.builder()
                .productId(orderRequest.productId())
                .quantity(orderRequest.quantity())
                .totalPrice(orderRequest.totalPrice())
                .status(OrderStatus.PENDING)
                .build();

        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                savedOrder.getTotalPrice()
        );

        log.info("Publishing OrderCreatedEvent: {}", event);
        kafkaTemplate.send("order-events", event.orderId().toString(), event);

        return savedOrder;
    }
}
