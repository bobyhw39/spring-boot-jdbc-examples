package com.enigma.purchased.exceptions;

public class BadRequestException extends javax.ws.rs.BadRequestException {
    public BadRequestException(String message) {
        super(message);
    }
}
