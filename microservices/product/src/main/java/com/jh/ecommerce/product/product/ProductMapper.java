package com.jh.ecommerce.product.product;

import com.jh.ecommerce.product.category.Category;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductCreateRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .status(request.status() == null ? Product.ProductStatus.ACTIVE : request.status())
                .description(request.description())
                .quantityInStock(request.quantityInStock())
                .price(request.price())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build()
                )
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantityInStock(),
                product.getStatus(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }
    public ProductPurchaseResponse toProductPurchaseResponse(Product product, int quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }


}
