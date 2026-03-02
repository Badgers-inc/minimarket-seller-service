package org.badgers.sellerservice.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_category", schema = "seller_application")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    private String description;

    @Column(name = "category_code", nullable = false, unique = true, length = 20)
    private String categoryCode;

    @Column(nullable = false)
    private Boolean active;
}