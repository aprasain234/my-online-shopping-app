package com.ecommerce.inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public void deductStock(UUID productId, int quantity) {
        log.info("Deducting {} units for product {}", quantity, productId);
        int updatedRows = inventoryRepository.deductStock(productId, quantity);
        if (updatedRows == 0) {
            log.error("Failed to deduct stock for product {}. Either product not found or insufficient stock.", productId);
            throw new RuntimeException("Insufficient stock or product not found");
        }
        log.info("Successfully deducted stock for product {}", productId);
    }
}
