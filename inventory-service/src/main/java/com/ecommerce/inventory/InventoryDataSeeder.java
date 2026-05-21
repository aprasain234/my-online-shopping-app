package com.ecommerce.inventory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryDataSeeder implements CommandLineRunner {

    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) {
        if (inventoryRepository.count() == 0) {
            log.info("Seeding initial inventory data...");
            List<Inventory> initialInventory = List.of(
                    Inventory.builder()
                            .productId(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"))
                            .availableStock(100)
                            .build(),
                    Inventory.builder()
                            .productId(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"))
                            .availableStock(100)
                            .build(),
                    Inventory.builder()
                            .productId(UUID.fromString("550e8400-e29b-41d4-a716-446655440002"))
                            .availableStock(100)
                            .build()
            );
            inventoryRepository.saveAll(initialInventory);
            log.info("Inventory data seeded successfully.");
        } else {
            log.info("Inventory data already exists. Skipping seeding.");
        }
    }
}
