package org.badgers.sellerservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.badgers.sellerservice.api.dto.ProductCreateRequest;
import org.badgers.sellerservice.api.dto.ProductUpdateRequest;
import org.badgers.sellerservice.domain.model.ProductEntity;
import org.badgers.sellerservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Создание нового товара
     * POST /api/v1/products
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createProduct(@RequestBody ProductCreateRequest request) {
        return productService.createProduct(request);
    }

    /**
     * Получение списка всех активных товаров
     * GET /api/v1/products
     */
    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllActiveProducts();
    }

    /**
     * Получение детальной информации о товаре по ID
     * GET /api/v1/products/{id}
     */
    @GetMapping("/{id}")
    public ProductEntity getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

    /**
     * Обновление данных товара
     * PUT /api/v1/products/{id}
     */
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable UUID id, @RequestBody ProductUpdateRequest request) {
        productService.updateProduct(id, request);
    }

    /**
     * Дезактивация товара (удаление в кавычках)
     * DELETE /api/v1/products/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateProduct(@PathVariable UUID id) {
        productService.deactivateProduct(id);
    }
}