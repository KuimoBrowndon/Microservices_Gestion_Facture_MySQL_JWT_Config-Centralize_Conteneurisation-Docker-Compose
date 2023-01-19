package org.ms.billingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductItemRequestDTO {
    private String invoiceId;
    private String productId;
    private double quantity;
    private BigDecimal price;
}
