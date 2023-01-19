package org.ms.billingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ms.billingservice.entities.Customer;
import org.ms.billingservice.entities.ProductItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class InvoiceResponseDTO {
    private String id;
    private Date date;
    private BigDecimal amount;
    private Customer customer;
    private List<ProductItem> productItems;
}
