package org.ms.inventoryservice.mappers;

import java.math.BigDecimal;
import javax.annotation.Generated;
import org.ms.inventoryservice.dto.ProductRequestDTO;
import org.ms.inventoryservice.dto.ProductResponseDTO;
import org.ms.inventoryservice.entities.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-04T11:34:33+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponseDTO ProductToProductResponseDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        productResponseDTO.setId( product.getId() );
        productResponseDTO.setName( product.getName() );
        productResponseDTO.setPrice( BigDecimal.valueOf( product.getPrice() ) );

        return productResponseDTO;
    }

    @Override
    public Product ProductRequestDTOToProduct(ProductRequestDTO productRequestDTO) {
        if ( productRequestDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productRequestDTO.getName() );
        if ( productRequestDTO.getPrice() != null ) {
            product.setPrice( productRequestDTO.getPrice().doubleValue() );
        }

        return product;
    }
}
