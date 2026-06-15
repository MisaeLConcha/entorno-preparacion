package com.duoc.preparacion.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(
            String mensaje) {
        super(mensaje);
    }
}