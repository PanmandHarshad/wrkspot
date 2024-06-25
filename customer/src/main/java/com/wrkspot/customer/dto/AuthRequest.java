package com.wrkspot.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for authentication requests.
 * <p>
 * This class is used to capture the authentication request data,
 * specifically the username and password provided by the user.
 * It uses Lombok annotations to generate boilerplate code such as
 * getters, setters, constructors, and toString methods.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    /**
     * The username of the user attempting to authenticate.
     */
    private String username;

    /**
     * The password of the user attempting to authenticate.
     */
    private String password;
}
