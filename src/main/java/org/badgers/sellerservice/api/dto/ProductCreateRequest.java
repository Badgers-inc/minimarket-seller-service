package org.badgers.sellerservice.api.dto;

import lombok.Data;

@Data
public class ProductCreateRequest {
    private String productName;
    private String description;
    private String articleNumber;
}