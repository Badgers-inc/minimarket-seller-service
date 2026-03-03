package org.badgers.sellerservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    private String productName;

    private String description;

    private String articleNumber;

}