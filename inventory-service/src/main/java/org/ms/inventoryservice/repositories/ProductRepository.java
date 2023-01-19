package org.ms.inventoryservice.repositories;

import org.ms.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
    Product findProductByName(String name);
}
