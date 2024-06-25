package com.wrkspot.customer.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Schema(description = "Schema to hold Customer information")
public class Customer {

    @Schema(description = "First name of the customer", example = "John")
    @NotEmpty(message = "First name cannot be null or empty")
    @Size(min = 2, max = 30, message = "The length of the customer name should be between 2 and 30")
    private String firstName;

    @Schema(description = "Last name of the customer", example = "Doe")
    @NotEmpty(message = "Last name cannot be null or empty")
    @Size(min = 2, max = 30, message = "The length of the customer name should be between 2 and 30")
    private String lastName;

    @Id
    @Schema(description = "Unique identifier for the customer", example = "1")
    private String customerId;

    @Schema(description = "Age of the customer", example = "30")
    @Min(0)
    private int age;

    @Schema(description = "Spending limit for the customer", example = "1000.00")
    @DecimalMin(value = "0.0", message = "Spending limit must be positive")
    private BigDecimal spendingLimit;

    @Schema(description = "Mobile Number of Customer", example = "4354437687")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    @JsonManagedReference
    @Schema(description = "Addresses of the customer", implementation = Address.class)
    private List<Address> addresses = new ArrayList<>();
}
