package org.badgers.sellerservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.badgers.sellerservice.api.dto.OfferCreateRequest;
import org.badgers.sellerservice.service.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    /**
     * Создание нового предложения (оффера).
     * Согласно ТЗ:
     * - Эндпоинт: POST /api/v1/offers
     * - Требуется хедер X-Auth-User-ID
     * - Возвращает ID типа biginteger (Long)
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createOffer(
            @RequestHeader("X-Auth-User-ID") UUID sellerId,
            @RequestBody OfferCreateRequest request) {
        // Передаем параметры в том порядке, в котором они объявлены в OfferService
        return offerService.createOffer(request, sellerId);
    }
}