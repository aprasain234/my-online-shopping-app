package com.ecommerce.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    @Modifying
    @Query("UPDATE Inventory i SET i.availableStock = i.availableStock - :qty WHERE i.productId = :pid AND i.availableStock >= :qty")
    int deductStock(@Param("pid") UUID productId, @Param("qty") int quantity);
}
