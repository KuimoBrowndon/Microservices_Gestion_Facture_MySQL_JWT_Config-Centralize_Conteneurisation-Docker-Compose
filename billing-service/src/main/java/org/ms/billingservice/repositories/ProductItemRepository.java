package org.ms.billingservice.repositories;

import org.ms.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductItemRepository extends JpaRepository<ProductItem,String> {
    List<ProductItem> findByInvoiceId(String invoiceId);
    List<ProductItem> findByProductId(String productId);
}
