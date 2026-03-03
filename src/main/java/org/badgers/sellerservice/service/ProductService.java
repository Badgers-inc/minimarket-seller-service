package org.badgers.sellerservice.service;

import lombok.RequiredArgsConstructor;
import org.badgers.sellerservice.api.dto.ProductCreateRequest;
import org.badgers.sellerservice.api.dto.ProductUpdateRequest;
import org.badgers.sellerservice.domain.model.Product;
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
        Product product = Product.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .articleNumber(request.getArticleNumber())
                .active(true)
                .build();
        return productRepository.save(product).getId();
    }

    @Transactional(readOnly = true)
    public List<Product> getAllActiveProducts() {

        return productRepository.findAll().stream()
                .filter(Product::isActive)
                .toList();
    }

    @Transactional(readOnly = true)
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Товар не найден: " + id));
    }

    @Transactional
    public void updateProduct(UUID id, ProductUpdateRequest request) {
        Product product = getProductById(id);
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        productRepository.save(product);
    }

    @Transactional
    public void deactivateProduct(UUID id) {
        Product product = getProductById(id);
        product.setActive(false);
        productRepository.save(product);
    }
}