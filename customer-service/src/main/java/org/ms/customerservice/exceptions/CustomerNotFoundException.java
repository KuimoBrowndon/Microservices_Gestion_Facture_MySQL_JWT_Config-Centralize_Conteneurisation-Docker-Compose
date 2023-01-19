package org.ms.customerservice.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String messages) {
        super(messages);
    }
}
