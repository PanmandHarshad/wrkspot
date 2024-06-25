package com.wrkspot.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.wrkspot.customer.dto.ErrorResponseDto;

import java.time.LocalDateTime;

/**
 * Global exception handler to handle specific and global exceptions in a consistent manner.
 * <p>
 * This class provides centralized exception handling across all {@code @RequestMapping} methods.
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles all uncaught exceptions.
     * <p>
     * This method catches any exceptions that are not explicitly handled by other methods.
     * It creates an {@link ErrorResponseDto} object and returns it with an HTTP status of 500 (Internal Server Error).
     * </p>
     *
     * @param exception  the exception that was thrown
     * @param webRequest the web request during which the exception was thrown
     * @return a {@link ResponseEntity} containing the error response DTO and HTTP status 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception,
                                                                  WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles ResourceNotFoundException exceptions.
     * <p>
     * This method catches {@link ResourceNotFoundException} exceptions and returns a 404 (Not Found) status.
     * </p>
     *
     * @param exception  the exception that was thrown
     * @param webRequest the web request during which the exception was thrown
     * @return a {@link ResponseEntity} containing the error response DTO and HTTP status 404
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                            WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles CustomerAlreadyExistsException exceptions.
     * <p>
     * This method catches {@link CustomerAlreadyExistsException} exceptions and returns a 400 (Bad Request) status.
     * </p>
     *
     * @param exception  the exception that was thrown
     * @param webRequest the web request during which the exception was thrown
     * @return a {@link ResponseEntity} containing the error response DTO and HTTP status 400
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,
                                                                                 WebRequest webRequest) {
        ErrorResponseDto errorResponseDTO = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }
}
