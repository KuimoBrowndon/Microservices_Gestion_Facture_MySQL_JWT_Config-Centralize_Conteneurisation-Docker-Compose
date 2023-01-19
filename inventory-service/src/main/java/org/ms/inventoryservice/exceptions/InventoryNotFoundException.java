package org.ms.inventoryservice.exceptions;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(String messages) {
        super(messages);
    }
}
