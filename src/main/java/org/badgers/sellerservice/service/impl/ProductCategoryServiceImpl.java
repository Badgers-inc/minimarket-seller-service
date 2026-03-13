package org.badgers.sellerservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.badgers.sellerservice.entity.ProductCategory;
import org.badgers.sellerservice.repository.ProductCategoryRepository;
import org.badgers.sellerservice.service.ProductCategoryService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategory save(ProductCategory category) {
        log.debug("Сохранение категории товара: {}", category);
        return categoryRepository.save(category);
    }
}