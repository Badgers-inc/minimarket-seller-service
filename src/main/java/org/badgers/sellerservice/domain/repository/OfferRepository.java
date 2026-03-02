package org.badgers.sellerservice.domain.repository;

import org.badgers.sellerservice.domain.model.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
}