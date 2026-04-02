package org.badgers.sellerservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.badgers.sellerservice.entity.Offer;
import org.badgers.sellerservice.repository.OfferRepository;
import org.badgers.sellerservice.service.OfferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    @Transactional
    public Offer save(Offer offer) {
        log.debug("Сохранение торгового предложения для товара ID: {} от продавца ID: {}",
                offer.getProduct().getId(), offer.getSeller().getId());
        return offerRepository.save(offer);
    }
}