package org.badgers.sellerservice.api.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OfferCreateRequest {
    // ID товара, который мы хотим выставить на продажу
    private UUID productId;

    // Цена предложения
    private BigDecimal price;

    // Количество товара в наличии
    private Integer quantity;
}