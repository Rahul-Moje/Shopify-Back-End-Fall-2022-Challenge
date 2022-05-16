package com.myorganization.myteam.inventorymanagement.exception;

public class InventoryManagementException extends RuntimeException {

    public InventoryManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryManagementException(String message) {
        super(message);
    }
}
