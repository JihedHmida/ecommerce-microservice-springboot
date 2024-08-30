package com.jh.ecommerce.product.category;

import com.jh.ecommerce.product.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String iconUrl;
    private int order;

    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;


    @OneToMany(mappedBy = "parentCategory")
    private List<Category> subCategories;

}
