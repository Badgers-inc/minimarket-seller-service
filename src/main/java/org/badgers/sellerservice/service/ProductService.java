package org.badgers.sellerservice.service;

import lombok.RequiredArgsConstructor;
import org.badgers.sellerservice.api.dto.ProductCreateRequest;
import org.badgers.sellerservice.api.dto.ProductUpdateRequest;
import org.badgers.sellerservice.domain.model.ProductEntity;
import org.badgers.sellerservice.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public UUID createProduct(ProductCreateRequest request) {
        ProductEntity product = ProductEntity.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .articleNumber(request.getArticleNumber())
                .active(true) // При создании товар активен по умолчанию
                .build();
        return productRepository.save(product).getId();
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> getAllActiveProducts() {
        // Базовая реализация получения активных товаров
        return productRepository.findAll().stream()
                .filter(ProductEntity::isActive)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductEntity getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Товар не найден: " + id));
    }

    @Transactional
    public void updateProduct(UUID id, ProductUpdateRequest request) {
        ProductEntity product = getProductById(id);
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        productRepository.save(product);
    }

    @Transactional
    public void deactivateProduct(UUID id) {
        ProductEntity product = getProductById(id);
        product.setActive(false); // Дезактивация вместо удаления
        productRepository.save(product);
    }
}