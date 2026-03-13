package org.badgers.sellerservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.badgers.sellerservice.entity.Seller;
import org.badgers.sellerservice.repository.SellerRepository;
import org.badgers.sellerservice.service.SellerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    @Override
    public Seller save(Seller seller) {
        log.debug("Попытка сохранения продавца: {}", seller);
        return sellerRepository.save(seller);
    }
}