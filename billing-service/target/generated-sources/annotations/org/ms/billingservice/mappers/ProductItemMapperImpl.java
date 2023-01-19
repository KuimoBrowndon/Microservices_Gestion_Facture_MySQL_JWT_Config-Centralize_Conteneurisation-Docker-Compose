package org.ms.billingservice.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.ms.billingservice.dto.ProductItemRequestDTO;
import org.ms.billingservice.dto.ProductItemResponseDTO;
import org.ms.billingservice.entities.ProductItem;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-04T11:46:20+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
@Component
public class ProductItemMapperImpl implements ProductItemMapper {

    @Override
    public ProductItem ProductItemRequestDTOToProductItem(ProductItemRequestDTO productItemRequestDTO) {
        if ( productItemRequestDTO == null ) {
            return null;
        }

        ProductItem productItem = new ProductItem();

        productItem.setInvoiceId( productItemRequestDTO.getInvoiceId() );
        productItem.setProductId( productItemRequestDTO.getProductId() );
        productItem.setQuantity( productItemRequestDTO.getQuantity() );
        productItem.setPrice( productItemRequestDTO.getPrice() );

        return productItem;
    }

    @Override
    public ProductItemResponseDTO ProductItemToProductItemResponseDTO(ProductItem productItem) {
        if ( productItem == null ) {
            return null;
        }

        ProductItemResponseDTO productItemResponseDTO = new ProductItemResponseDTO();

        productItemResponseDTO.setId( productItem.getId() );
        productItemResponseDTO.setInvoiceId( productItem.getInvoiceId() );
        productItemResponseDTO.setProduct( productItem.getProduct() );
        productItemResponseDTO.setQuantity( productItem.getQuantity() );
        productItemResponseDTO.setPrice( productItem.getPrice() );

        return productItemResponseDTO;
    }

    @Override
    public List<ProductItem> LProductItemResponseDTOToLProductItem(List<ProductItemResponseDTO> productItemResponseDTOS) {
        if ( productItemResponseDTOS == null ) {
            return null;
        }

        List<ProductItem> list = new ArrayList<ProductItem>( productItemResponseDTOS.size() );
        for ( ProductItemResponseDTO productItemResponseDTO : productItemResponseDTOS ) {
            list.add( productItemResponseDTOToProductItem( productItemResponseDTO ) );
        }

        return list;
    }

    protected ProductItem productItemResponseDTOToProductItem(ProductItemResponseDTO productItemResponseDTO) {
        if ( productItemResponseDTO == null ) {
            return null;
        }

        ProductItem productItem = new ProductItem();

        productItem.setId( productItemResponseDTO.getId() );
        productItem.setInvoiceId( productItemResponseDTO.getInvoiceId() );
        productItem.setProduct( productItemResponseDTO.getProduct() );
        productItem.setQuantity( productItemResponseDTO.getQuantity() );
        productItem.setPrice( productItemResponseDTO.getPrice() );

        return productItem;
    }
}
