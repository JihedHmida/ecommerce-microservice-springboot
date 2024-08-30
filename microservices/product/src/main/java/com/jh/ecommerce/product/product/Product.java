package com.jh.ecommerce.product.product;

import com.jh.ecommerce.product.category.Category;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {
    public enum ProductStatus {
        ACTIVE,
        INACTIVE,
        DISCONTINUED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 500)
    private String description;
    @Column(precision = 100, scale = 3)
    private BigDecimal price;

    private int quantityInStock;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastUpdatedDate;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}

