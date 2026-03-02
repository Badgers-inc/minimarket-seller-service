package org.badgers.sellerservice.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "seller", schema = "seller_application")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SellerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;
}