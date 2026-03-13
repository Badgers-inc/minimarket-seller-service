package org.badgers.sellerservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.badgers.sellerservice.entity.Product;
import org.badgers.sellerservice.repository.ProductRepository;
import org.badgers.sellerservice.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        log.debug("Сохранение нового товара: {}", product);
        return productRepository.save(product);
    }
}