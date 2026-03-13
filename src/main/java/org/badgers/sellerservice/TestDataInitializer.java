package org.badgers.sellerservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.badgers.sellerservice.entity.*;
import org.badgers.sellerservice.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Profile("test")
@RequiredArgsConstructor
@Slf4j
public class TestDataInitializer implements CommandLineRunner {

    private final SellerService sellerService;
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;
    private final OfferService offerService;


    private final List<ProductCategory> savedCategories = new ArrayList<>();
    private final List<Seller> savedSellers = new ArrayList<>();
    private final List<Product> savedProducts = new ArrayList<>();

    @Override
    @Transactional
    public void run(String... args) {
        log.debug("=========================================================");
        log.debug("STARTING DETAILED TEST DATA INITIALIZATION");
        log.debug("=========================================================");

        try {
            createProductCategories();
            createSellers();
            createProducts();
            createOffers();

            log.debug("=========================================================");
            log.debug("TEST DATA INITIALIZATION COMPLETED SUCCESSFULLY");
            log.debug("=========================================================");
        } catch (Exception e) {
            log.error("CRITICAL ERROR DURING INITIALIZATION: {}", e.getMessage(), e);
            throw e;
        }
    }

    private void createProductCategories() {
        log.debug("Step 1: Creating categories from specification...");
        String[][] data = {
                {"Электроника", "ELECTRONICS"},
                {"Бытовая техника", "APPLIANCES"},
                {"Одежда", "CLOTHING"}
        };

        for (String[] cat : data) {
            ProductCategory c = ProductCategory.builder()
                    .description(cat[0])
                    .categoryCode(cat[1])
                    .active(true)
                    .build();
            savedCategories.add(productCategoryService.save(c));
        }
        log.debug("Step 1 completed: {} categories created", savedCategories.size());
    }

    private void createSellers() {
        log.debug("Step 2: Creating sellers...");
        String[] names = {"ТехноМир", "Гаджет Лаб", "Модный Уголок"};

        for (String name : names) {
            Seller s = Seller.builder()
                    .name(name)
                    .active(true)
                    .build();
            savedSellers.add(sellerService.save(s));
        }
        log.debug("Step 2 completed: {} sellers created", savedSellers.size());
    }

    private void createProducts() {
        log.debug("Step 3: Creating products and linking to categories...");

        Product p1 = Product.builder()
                .productName("Смартфон X1")
                .articleNumber("ART-001")
                .description("Флагман")
                .categories(Set.of(savedCategories.get(0)))
                .active(true)
                .build();

        Product p2 = Product.builder()
                .productName("Кофемашина")
                .articleNumber("ART-002")
                .description("Автоматическая")
                .categories(Set.of(savedCategories.get(1)))
                .active(true)
                .build();

        savedProducts.add(productService.save(p1));
        savedProducts.add(productService.save(p2));
        log.debug("Step 3 completed: {} products created", savedProducts.size());
    }

    private void createOffers() {
        log.debug("Step 4: Creating commercial offers (linking Products and Sellers)...");

        Offer o1 = Offer.builder()
                .product(savedProducts.get(0))
                .seller(savedSellers.get(0))
                .price(new BigDecimal("5000.00"))
                .quantity(5)
                .active(true)
                .build();

        Offer o2 = Offer.builder()
                .product(savedProducts.get(0))
                .seller(savedSellers.get(1))
                .price(new BigDecimal("4800.00"))
                .quantity(2)
                .active(true)
                .build();

        offerService.save(o1);
        offerService.save(o2);
        log.debug("Step 4 completed: Offers initialized");
    }
}