package com.wrkspot.customer.exception;

import com.wrkspot.customer.dto.ErrorResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleGlobalException() {
        Exception exception = new Exception("Test exception");

        when(webRequest.getDescription(false)).thenReturn("Test description");

        ResponseEntity<ErrorResponseDto> response = globalExceptionHandler.handleGlobalException(exception, webRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ErrorResponseDto errorResponse = response.getBody();
        assertNotNull(errorResponse);
        assertEquals("Test description", errorResponse.getApiPath());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse.getErrorCode());
        assertEquals("Test exception", errorResponse.getErrorMessage());
        assertNotNull(errorResponse.getErrorTime());
    }

    @Test
    void testHandleResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource");

        when(webRequest.getDescription(false)).thenReturn("Test description");

        ResponseEntity<ErrorResponseDto> response = globalExceptionHandler.handleResourceNotFoundException(exception, webRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponseDto errorResponse = response.getBody();
        assertNotNull(errorResponse);
        assertEquals("Test description", errorResponse.getApiPath());
        assertEquals(HttpStatus.NOT_FOUND, errorResponse.getErrorCode());
        assertEquals("Resource not found", errorResponse.getErrorMessage());
        assertNotNull(errorResponse.getErrorTime());
    }

    @Test
    void testHandleCustomerAlreadyExistsException() {
        CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException("Customer already exists");

        when(webRequest.getDescription(false)).thenReturn("Test description");

        ResponseEntity<ErrorResponseDto> response = globalExceptionHandler.handleCustomerAlreadyExistsException(exception, webRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorResponseDto errorResponse = response.getBody();
        assertNotNull(errorResponse);
        assertEquals("Test description", errorResponse.getApiPath());
        assertEquals(HttpStatus.BAD_REQUEST, errorResponse.getErrorCode());
        assertEquals("Customer already exists", errorResponse.getErrorMessage());
        assertNotNull(errorResponse.getErrorTime());
    }
}
