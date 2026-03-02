package org.badgers.sellerservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.badgers.sellerservice.api.dto.OfferCreateRequest;
import org.badgers.sellerservice.domain.model.OfferEntity;
import org.badgers.sellerservice.domain.model.ProductEntity;
import org.badgers.sellerservice.domain.model.SellerEntity;
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

    /**
     * Создание предложения строго по ТЗ.
     * Складские остатки в локальной БД не хранятся.
     */
    @Transactional
    public Long createOffer(OfferCreateRequest request, UUID sellerId) {
        // 1. Поиск продавца
        SellerEntity seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new EntityNotFoundException("Seller not found with id: " + sellerId));

        // 2. Поиск товара
        ProductEntity product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + request.getProductId()));

        // 3. Создание сущности (без quantity, только поля из таблицы offer)
        OfferEntity offer = OfferEntity.builder()
                .product(product)
                .seller(seller)
                .price(request.getPrice())
                .active(true) // Поле обязательно
                .build();

        // 4. Сохранение и возврат ID (тип Long)
        OfferEntity savedOffer = offerRepository.save(offer);

        return savedOffer.getId();
    }
}