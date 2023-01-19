package org.ms.billingservice.services;

import org.ms.billingservice.dto.InvoiceRequestDTO;
import org.ms.billingservice.dto.InvoiceResponseDTO;

import java.util.List;

public interface InvoiceService {
    InvoiceResponseDTO saveInvoice(String BearerToken, InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO getInvoice(String BearerToken, String invoiceId);
    List<InvoiceResponseDTO> listInvoices(String BearerToken);
    List<InvoiceResponseDTO> invoicesByCustomerId(String BearerToken, String customerId);
    InvoiceResponseDTO updateInvoice(String BearerToken, String invoiceId, InvoiceRequestDTO invoiceRequestDTO);
    void delete(String BearerToken, String invoiceId);
}
