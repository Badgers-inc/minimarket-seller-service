package org.badgers.sellerservice.api.dto;

import lombok.Data;

@Data
public class ProductUpdateRequest {
    private String productName;
    private String description;
}