package com.ecommerce.order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderRequest(
    UUID productId,
    Integer quantity,
    BigDecimal totalPrice
) {}
