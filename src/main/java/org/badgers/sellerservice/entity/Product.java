package org.badgers.sellerservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "product", schema = "seller_application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "categories")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
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

    @Column(name = "active")
    @Builder.Default
    private boolean active = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_product_category",
            schema = "seller_application",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<ProductCategory> categories;
}