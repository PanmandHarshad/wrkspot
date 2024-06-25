package com.wrkspot.customer.controller;

import com.wrkspot.customer.dto.AuthRequest;
import com.wrkspot.customer.dto.CustomersLists;
import com.wrkspot.customer.dto.ErrorResponseDto;
import com.wrkspot.customer.entity.Customer;
import com.wrkspot.customer.service.CustomerService;
import com.wrkspot.customer.service.JwtService;
import com.wrkspot.customer.util.CustomerUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling customer-related operations.
 * <p>
 * This controller provides endpoints for creating customers,
 * retrieving customer details with optional filters, and authenticating users.
 * Only admin users can create customer records, while other authenticated users
 * can fetch customer information.
 */
@Tag(
        name = "Creating and fetching the customer information",
        description = "Only admin user is allowed to create customer record, " +
                "other authenticated users are allowed to fetch customer information using filters also"
)
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Operation(
            summary = "Create new customer if not exist, otherwise updating the existing customers",
            description = "Creating new customer based on provided details of customers"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> createMultipleCustomers(@RequestBody List<Customer> customers) {
        List<Customer> createdCustomers = customerService.createCustomers(customers);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomers);
    }

    @Operation(
            summary = "Fetch customer details based on provided request parameters," +
                    " if request parameter is not provided then returning all available customers.",
            description = "Retrieving the customers based on provided request parameters, otherwise fetch all customers"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomersWithFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state) {
        List<Customer> customers = customerService.getCustomersWithFilter(name, city, state);
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @Operation(
            summary = "Generate token for authenticated user",
            description = "Create token for authenticated user, token will be used for authorization purpose"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping(value = "/authenticate", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(token);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

    @Operation(
            summary = "Get customers only in list A",
            description = "Retrieve customers that are only in list A"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping(value = "/only-in-a", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomersOnlyInA(@RequestBody CustomersLists customersLists) {
        List<Customer> customers = CustomerUtils.customersOnlyInA(customersLists.getListA(), customersLists.getListB());
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @Operation(
            summary = "Get customers only in list B",
            description = "Retrieve customers that are only in list B"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping(value = "/only-in-b", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomersOnlyInB(@RequestBody CustomersLists customersLists) {
        List<Customer> customers = CustomerUtils.customersOnlyInB(customersLists.getListA(), customersLists.getListB());
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @Operation(
            summary = "Get customers in both lists A and B",
            description = "Retrieve customers that are in both lists A and B"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping(value = "/in-both-a-and-b", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getCustomersInBothAAndB(@RequestBody CustomersLists customersLists) {
        List<Customer> customers = CustomerUtils.customersInBothAAndB(customersLists.getListA(), customersLists.getListB());
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
}
