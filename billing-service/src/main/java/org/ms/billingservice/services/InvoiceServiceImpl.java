package org.ms.billingservice.services;

import org.ms.billingservice.dto.InvoiceRequestDTO;
import org.ms.billingservice.dto.InvoiceResponseDTO;
import org.ms.billingservice.dto.ProductItemResponseDTO;
import org.ms.billingservice.entities.Customer;
import org.ms.billingservice.entities.Invoice;
import org.ms.billingservice.entities.ProductItem;
import org.ms.billingservice.exceptions.InvoiceNotFoundException;
import org.ms.billingservice.mappers.*;
import org.ms.billingservice.openfeign.CustomerRestClient;
import org.ms.billingservice.repositories.InvoiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final ProductItemService productItemService;
    private final ProductItemMapper productItemMapper;
    private final CustomerRestClient customerRestClient;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, ProductItemService productItemService, ProductItemMapper productItemMapper, CustomerRestClient customerRestClient) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.productItemService = productItemService;
        this.productItemMapper = productItemMapper;
        this.customerRestClient = customerRestClient;
    }

    @Override
    public InvoiceResponseDTO saveInvoice(String BearerToken, InvoiceRequestDTO invoiceRequestDTO) {
        if (invoiceRequestDTO.getAmount().equals(BigDecimal.valueOf(0)) || invoiceRequestDTO.getCustomerId().equals(""))
            throw new InvoiceNotFoundException("Vous n'avez pas envoyé toutes les données requises");
        Customer customer = customerRestClient.getCustomer(BearerToken,invoiceRequestDTO.getCustomerId());
        if (customer == null)
            throw new InvoiceNotFoundException("Ce client n'existe pas");
        Invoice invoice = invoiceMapper.InvoiceRequestDTOToInvoice(invoiceRequestDTO);
        invoice.setId(UUID.randomUUID().toString());
        invoice.setDate(new Date());
        Invoice invoiceSave = invoiceRepository.save(invoice);
        invoiceSave.setCustomer(customer);
        return invoiceMapper.InvoiceToInvoiceResponseDTO(invoiceSave);
    }

    @Override
    public InvoiceResponseDTO getInvoice(String BearerToken, String invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId).orElse(null);
        if (invoice == null)
            throw new InvoiceNotFoundException("Cette facture n'existe pas");
        Customer customer = customerRestClient.getCustomer(BearerToken,invoice.getCustomerId());
        invoice.setCustomer(customer);
        List<ProductItemResponseDTO> productItemResponseDTOS = productItemService.productItemByInvoiceId(BearerToken,invoiceId);
        List<ProductItem> productItems = productItemMapper.LProductItemResponseDTOToLProductItem(productItemResponseDTOS);
        invoice.setProductItems(productItems);
        return invoiceMapper.InvoiceToInvoiceResponseDTO(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> listInvoices(String BearerToken) {
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice:invoices){
            Customer customer = customerRestClient.getCustomer(BearerToken,invoice.getCustomerId());
            invoice.setCustomer(customer);
            List<ProductItemResponseDTO> productItemResponseDTOS = productItemService.productItemByInvoiceId(BearerToken,invoice.getId());
            List<ProductItem> productItems = productItemMapper.LProductItemResponseDTOToLProductItem(productItemResponseDTOS);
            invoice.setProductItems(productItems);
        }
        return invoices.stream()
                .map(invoiceMapper::InvoiceToInvoiceResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceResponseDTO> invoicesByCustomerId(String BearerToken, String customerId) {
        Customer customer = customerRestClient.getCustomer(BearerToken,customerId);
        if (customer == null)
            throw new InvoiceNotFoundException("Ce client n'existe pas");
        else if (invoiceRepository.findByCustomerId(customerId).isEmpty())
            throw new InvoiceNotFoundException("Ce client existe mais n'a pas de facture");
        else{
            List<Invoice> invoices = invoiceRepository.findByCustomerId(customerId);
            for (Invoice invoice:invoices) {
                invoice.setCustomer(customer);
                List<ProductItemResponseDTO> productItemResponseDTOS = productItemService.productItemByInvoiceId(BearerToken,invoice.getId());
                List<ProductItem> productItems = productItemMapper.LProductItemResponseDTOToLProductItem(productItemResponseDTOS);
                invoice.setProductItems(productItems);
            }
            return invoices.stream()
                    .map(invoiceMapper::InvoiceToInvoiceResponseDTO)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public InvoiceResponseDTO updateInvoice(String BearerToken, String invoiceId, InvoiceRequestDTO invoiceRequestDTO) {
        if (invoiceRequestDTO.getAmount().equals(BigDecimal.valueOf(0)) || invoiceRequestDTO.getCustomerId().equals(""))
            throw new InvoiceNotFoundException("Vous n'avez pas envoyé toutes les données requises");
        Invoice invoiceUpdate = invoiceRepository.findById(invoiceId).orElse(null);
        if (invoiceUpdate == null)
            throw new InvoiceNotFoundException("Cette facture n'existe pas");
        Customer customer = customerRestClient.getCustomer(BearerToken,invoiceRequestDTO.getCustomerId());
        if (customer == null)
            throw new InvoiceNotFoundException("Ce client n'existe pas");
        Invoice invoice = invoiceMapper.InvoiceRequestDTOToInvoice(invoiceRequestDTO);
        invoice.setId(invoiceId);
        invoice.setDate(new Date());
        invoiceUpdate = invoiceRepository.save(invoice);
        invoiceUpdate.setCustomer(customer);
        return invoiceMapper.InvoiceToInvoiceResponseDTO(invoiceUpdate);
    }

    @Override
    public void delete(String BearerToken, String invoiceId){
        if(invoiceRepository.findById(invoiceId).isPresent()) {
            List<ProductItemResponseDTO> productItemResponseDTOS = productItemService.productItemByInvoiceId(BearerToken,invoiceId);
            for (ProductItemResponseDTO productItemResponseDTO:productItemResponseDTOS)
                productItemService.deleteProductItem(productItemResponseDTO.getId());
            invoiceRepository.deleteById(invoiceId);
        }
        else throw new InvoiceNotFoundException("Cette facture n'existe pas");
    }
}
