package org.ms.inventoryservice.mappers;

import org.mapstruct.Mapper;
import org.ms.inventoryservice.dto.ProductRequestDTO;
import org.ms.inventoryservice.dto.ProductResponseDTO;
import org.ms.inventoryservice.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponseDTO ProductToProductResponseDTO(Product product);
    Product ProductRequestDTOToProduct(ProductRequestDTO productRequestDTO);
}
