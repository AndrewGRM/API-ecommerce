package br.com.ramos.ecommerce.exception;

import java.time.LocalDateTime;

public record ErroResponse(
        LocalDateTime timeStamp,
        int status,
        String error,
        String message,
        String path
) {}
