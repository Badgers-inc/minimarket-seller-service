package org.badgers.sellerservice.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "product", schema = "seller_application")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_name", nullable = false, length = 70)
    private String productName;

    @Column(length = 3000, nullable = false)
    private String description;

    @Column(name = "article_number", nullable = false, unique = true, length = 30)
    private String articleNumber;

    @org.hibernate.annotations.CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @org.hibernate.annotations.UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(nullable = false)
    private boolean active;

    @ManyToMany
    @JoinTable(
            name = "product_product_category", // имя из твоего README
            schema = "seller_application",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<ProductCategoryEntity> categories;
}