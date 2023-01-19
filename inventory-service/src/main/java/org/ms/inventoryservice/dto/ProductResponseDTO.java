package org.ms.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductResponseDTO {
    private String id;
    private String name;
    private BigDecimal price;
}
