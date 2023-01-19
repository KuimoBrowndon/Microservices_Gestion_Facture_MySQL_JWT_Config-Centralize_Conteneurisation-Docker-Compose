package org.ms.billingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ms.billingservice.entities.Product;

import java.math.BigDecimal;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductItemResponseDTO {
    private String id;
    private String invoiceId;
    private Product product;
    private double quantity;
    private BigDecimal price;
}
