
package org.badgers.sellerservice.entity;

import jakarta.persistence.*;
        import lombok.*;
        import java.util.UUID;

@Entity
@Table(name = "seller", schema = "seller_application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;
}