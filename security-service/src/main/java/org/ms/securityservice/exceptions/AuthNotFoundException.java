package org.ms.securityservice.exceptions;

public class AuthNotFoundException extends RuntimeException {
    public AuthNotFoundException(String messages) {
        super(messages);
    }
}
