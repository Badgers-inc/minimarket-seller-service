package org.badgers.sellerservice.api.controller;

import lombok.RequiredArgsConstructor;
import org.badgers.sellerservice.api.dto.ProductCreateRequest;
import org.badgers.sellerservice.api.dto.ProductUpdateRequest;
import org.badgers.sellerservice.domain.model.Product;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createProduct(@RequestBody ProductCreateRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllActiveProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable UUID id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable UUID id, @RequestBody ProductUpdateRequest request) {
        productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateProduct(@PathVariable UUID id) {
        productService.deactivateProduct(id);
    }
}