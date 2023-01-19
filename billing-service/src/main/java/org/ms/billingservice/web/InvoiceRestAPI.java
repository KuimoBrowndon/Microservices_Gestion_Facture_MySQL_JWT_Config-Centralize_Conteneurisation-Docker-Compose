package org.ms.billingservice.web;

import org.ms.billingservice.dto.InvoiceRequestDTO;
import org.ms.billingservice.dto.InvoiceResponseDTO;
import org.ms.billingservice.services.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class InvoiceRestAPI {
    private final InvoiceService invoiceService;

    public InvoiceRestAPI(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @GetMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO getInvoice(@RequestHeader("Authorization") String BearerToken, @PathVariable(name = "id") String invoiceId){
        return invoiceService.getInvoice(BearerToken,invoiceId);
    }

    @GetMapping(path = "/invoices")
    public List<InvoiceResponseDTO> listInvoices(@RequestHeader("Authorization") String BearerToken){
        return invoiceService.listInvoices(BearerToken);
    }

    @GetMapping(path = "/invoices/ByCustomer/{customerId}")
    public List<InvoiceResponseDTO> getInvoiceByCustomer(@RequestHeader("Authorization") String BearerToken, @PathVariable String customerId){
        return invoiceService.invoicesByCustomerId(BearerToken, customerId);
    }

    @PostMapping(path = "/invoices")
    public InvoiceResponseDTO save(@RequestHeader("Authorization") String BearerToken, @RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.saveInvoice(BearerToken, invoiceRequestDTO);
    }

    @PutMapping(path = "/invoices/{id}")
    public InvoiceResponseDTO updateInvoice(@RequestHeader("Authorization") String BearerToken, @PathVariable String id, @RequestBody InvoiceRequestDTO invoiceRequestDTO){
        return invoiceService.updateInvoice(BearerToken, id, invoiceRequestDTO);
    }

    @DeleteMapping(path = "/invoices/{id}")
    public void delete(@RequestHeader("Authorization") String BearerToken, @PathVariable String id){
        invoiceService.delete(BearerToken, id);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
