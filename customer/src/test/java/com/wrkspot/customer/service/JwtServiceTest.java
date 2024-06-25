package com.wrkspot.customer.service;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @Test
    void testGenerateToken() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");

        String token = jwtService.generateToken(userDetails.getUsername());

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testValidateTokenSuccess() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");

        String token = jwtService.generateToken(userDetails.getUsername());
        boolean isValid = jwtService.validateToken(token, userDetails);

        assertTrue(isValid);
    }

    @Test
    void testValidateTokenFailure() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");

        String invalidToken = "eyJhbGciOiJIUzM4NCJ9" +
                ".eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcxOTIyMjIzMCwiZXhwIjoxNzUwNzU4MjMwfQ" +
                ".JWrj_OBhC680V4ttLW-b9kCXVLofSSGGFgGSupA0RxC6rPSEwZiT4mxr-qCUK_DN";
        boolean isValid = jwtService.validateToken(invalidToken, userDetails);

        assertFalse(isValid);
    }

    @Test
    void testExtractUsername() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("user");

        String token = jwtService.generateToken(userDetails.getUsername());
        String username = jwtService.extractUsername(token);

        assertEquals("user", username);
    }
}
