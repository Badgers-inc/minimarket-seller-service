package org.badgers.sellerservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_category", schema = "seller_application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 70)
    private String description;

    @Column(name = "category_code", nullable = false, unique = true, length = 20)
    @EqualsAndHashCode.Include
    private String categoryCode;

    @Column(name = "active")
    @Builder.Default
    private boolean active = true;
}