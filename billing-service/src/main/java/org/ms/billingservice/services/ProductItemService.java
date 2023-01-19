package org.ms.billingservice.services;

import org.ms.billingservice.dto.ProductItemRequestDTO;
import org.ms.billingservice.dto.ProductItemResponseDTO;

import java.util.List;

public interface ProductItemService {
    ProductItemResponseDTO saveProductItem(String BearerToken, ProductItemRequestDTO productItemRequestDTO);
    ProductItemResponseDTO getProductItem(String BearerToken, String id);
    List<ProductItemResponseDTO> listProductItem(String BearerToken);
    List<ProductItemResponseDTO> productItemByInvoiceId(String BearerToken, String invoiceId);
    List<ProductItemResponseDTO> productItemByProductId(String BearerToken, String productId);
    ProductItemResponseDTO updateProductItem(String BearerToken, String productItemId, ProductItemRequestDTO productItemRequestDTO);
    void deleteProductItem(String productItemId);
}
