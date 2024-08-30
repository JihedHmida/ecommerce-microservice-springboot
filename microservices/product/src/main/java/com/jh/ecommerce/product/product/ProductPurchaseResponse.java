package com.jh.ecommerce.product.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Long productId,
        String name,
        String description,
        BigDecimal price,
        int quantity
) {
}
