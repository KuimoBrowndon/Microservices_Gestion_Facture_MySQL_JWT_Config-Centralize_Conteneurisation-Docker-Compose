package org.ms.billingservice.mappers;

import org.mapstruct.Mapper;
import org.ms.billingservice.dto.ProductItemRequestDTO;
import org.ms.billingservice.dto.ProductItemResponseDTO;
import org.ms.billingservice.entities.ProductItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductItemMapper {
    ProductItem ProductItemRequestDTOToProductItem(ProductItemRequestDTO productItemRequestDTO);
    ProductItemResponseDTO ProductItemToProductItemResponseDTO(ProductItem productItem);
    List<ProductItem> LProductItemResponseDTOToLProductItem(List<ProductItemResponseDTO> productItemResponseDTOS);
}
