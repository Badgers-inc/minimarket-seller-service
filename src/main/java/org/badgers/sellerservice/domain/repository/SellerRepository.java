package org.badgers.sellerservice.domain.repository;

import org.badgers.sellerservice.domain.model.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, UUID> {
}