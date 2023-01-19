package org.ms.billingservice.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.ms.billingservice.dto.InvoiceRequestDTO;
import org.ms.billingservice.dto.InvoiceResponseDTO;
import org.ms.billingservice.entities.Invoice;
import org.ms.billingservice.entities.ProductItem;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-04T11:46:20+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 1.8.0_331 (Oracle Corporation)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Override
    public Invoice InvoiceRequestDTOToInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        if ( invoiceRequestDTO == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setAmount( invoiceRequestDTO.getAmount() );
        invoice.setCustomerId( invoiceRequestDTO.getCustomerId() );

        return invoice;
    }

    @Override
    public InvoiceResponseDTO InvoiceToInvoiceResponseDTO(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceResponseDTO invoiceResponseDTO = new InvoiceResponseDTO();

        invoiceResponseDTO.setId( invoice.getId() );
        invoiceResponseDTO.setDate( invoice.getDate() );
        invoiceResponseDTO.setAmount( invoice.getAmount() );
        invoiceResponseDTO.setCustomer( invoice.getCustomer() );
        List<ProductItem> list = invoice.getProductItems();
        if ( list != null ) {
            invoiceResponseDTO.setProductItems( new ArrayList<ProductItem>( list ) );
        }

        return invoiceResponseDTO;
    }

    @Override
    public Invoice InvoiceResponseDTOToInvoice(InvoiceResponseDTO invoiceResponseDTO) {
        if ( invoiceResponseDTO == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setId( invoiceResponseDTO.getId() );
        invoice.setDate( invoiceResponseDTO.getDate() );
        invoice.setAmount( invoiceResponseDTO.getAmount() );
        invoice.setCustomer( invoiceResponseDTO.getCustomer() );
        List<ProductItem> list = invoiceResponseDTO.getProductItems();
        if ( list != null ) {
            invoice.setProductItems( new ArrayList<ProductItem>( list ) );
        }

        return invoice;
    }
}
