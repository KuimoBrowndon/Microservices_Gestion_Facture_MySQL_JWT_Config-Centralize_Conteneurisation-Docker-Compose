package org.ms.billingservice.exceptions;

public class InvoiceNotFoundException extends RuntimeException {
    public InvoiceNotFoundException(String messages) {
        super(messages);
    }
}
