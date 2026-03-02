package org.badgers.sellerservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.badgers.sellerservice.api.dto.SellerCreateRequest;
import org.badgers.sellerservice.domain.model.SellerEntity;
import org.badgers.sellerservice.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createSeller(@RequestBody SellerCreateRequest request) {
        return sellerService.createSeller(request);
    }

    @GetMapping
    public List<SellerEntity> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/{id}")
    public SellerEntity getSellerById(@PathVariable UUID id) {
        return sellerService.getSellerById(id);
    }

    @GetMapping("/me")
    public SellerEntity getMe(@RequestHeader("X-Auth-User-ID") UUID userId) {
        return sellerService.getSellerById(userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeller(@PathVariable UUID id) {
        sellerService.deleteSeller(id);
    }
}