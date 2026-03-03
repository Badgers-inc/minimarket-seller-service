package org.badgers.sellerservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.badgers.sellerservice.api.dto.OfferCreateRequest;
import org.badgers.sellerservice.domain.model.Offer;
import org.badgers.sellerservice.domain.model.Product;
import org.badgers.sellerservice.domain.model.Seller;
import org.badgers.sellerservice.domain.repository.OfferRepository;
import org.badgers.sellerservice.domain.repository.ProductRepository;
import org.badgers.sellerservice.domain.repository.SellerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long createOffer(OfferCreateRequest request, UUID sellerId) {

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found with id: " + sellerId));


        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + request.getProductId()));


        Offer offer = Offer.builder()
                .product(product)
                .seller(seller)
                .price(request.getPrice())
                .active(true)
                .build();


        Offer savedOffer = offerRepository.save(offer);

        return savedOffer.getId();
    }
}