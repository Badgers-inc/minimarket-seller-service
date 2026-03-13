package org.badgers.sellerservice.repository;

import org.badgers.sellerservice.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {
}