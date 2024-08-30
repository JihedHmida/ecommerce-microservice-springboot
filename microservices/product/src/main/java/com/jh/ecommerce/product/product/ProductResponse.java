package com.jh.ecommerce.product.product;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String description,
        int quantityInStock,
        Product.ProductStatus status,
        BigDecimal price,
        Long categoryId,
        String categoryName,
        String categoryDescription
) {
}
