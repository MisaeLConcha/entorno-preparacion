package com.duoc.preparacion.exception;

import com.duoc.preparacion.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse>
    manejarNoEncontrado(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        ErrorResponse error =
                new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "Not Found",
                        ex.getMessage(),
                        request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(
            BadRequestException.class)
    public ResponseEntity<ErrorResponse>
    manejarBadRequest(
            BadRequestException ex,
            HttpServletRequest request) {

        ErrorResponse error =
                new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Bad Request",
                        ex.getMessage(),
                        request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>
    manejarValidaciones(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String mensaje =
                ex.getBindingResult()
                        .getFieldError()
                        .getDefaultMessage();

        ErrorResponse error =
                new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation Error",
                        mensaje,
                        request.getRequestURI());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    manejarGeneral(
            Exception ex,
            HttpServletRequest request) {

        ErrorResponse error =
                new ErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal Server Error",
                        ex.getMessage(),
                        request.getRequestURI());

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(
    HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse>
    manejarMetodoNoPermitido(
        HttpRequestMethodNotSupportedException ex,
        HttpServletRequest request) {

    ErrorResponse error =
            new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.METHOD_NOT_ALLOWED.value(),
                    "Method Not Allowed",
                    ex.getMessage(),
                    request.getRequestURI());
    return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(error);
        }
}