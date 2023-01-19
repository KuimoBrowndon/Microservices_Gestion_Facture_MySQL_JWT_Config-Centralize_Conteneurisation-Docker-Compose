package org.ms.billingservice.mappers;

import org.mapstruct.Mapper;
import org.ms.billingservice.dto.InvoiceRequestDTO;
import org.ms.billingservice.dto.InvoiceResponseDTO;
import org.ms.billingservice.entities.Invoice;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    Invoice InvoiceRequestDTOToInvoice(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO InvoiceToInvoiceResponseDTO(Invoice invoice);
    Invoice InvoiceResponseDTOToInvoice(InvoiceResponseDTO invoiceResponseDTO);
}
