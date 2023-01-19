package org.ms.inventoryservice.services;

import org.ms.inventoryservice.dto.ProductRequestDTO;
import org.ms.inventoryservice.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO saveProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO getProduct(String id);
    ProductResponseDTO updateProduct(String id, ProductRequestDTO productRequestDTO);
    List<ProductResponseDTO> listProducts();
    void deleteProduct(String id);
}