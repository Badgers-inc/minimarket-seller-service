package org.badgers.sellerservice.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.badgers.sellerservice.api.dto.SellerCreateRequest;
import org.badgers.sellerservice.domain.model.SellerEntity;
import org.badgers.sellerservice.domain.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    @Transactional
    public UUID createSeller(SellerCreateRequest request) {
        SellerEntity seller = SellerEntity.builder()
                .name(request.getName())
                .build();
        return sellerRepository.save(seller).getId();
    }

    @Transactional(readOnly = true)
    public List<SellerEntity> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public SellerEntity getSellerById(UUID id) {
        return sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Продавец не найден: " + id));
    }

    @Transactional
    public void deleteSeller(UUID id) {
        // "дезактивация", если в таблице нет поля active,
        sellerRepository.deleteById(id);
    }
}