package org.ms.billingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class ProductItem {
    @Id
    private String id;
    private String invoiceId;
    private String productId;
    @Transient
    private Product product;
    private double quantity;
    private BigDecimal price;
}
