package com.wrkspot.customer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
@Schema(name = "Address", description = "Schema to hold Address information")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the address", example = "1")
    private Long id;

    @Schema(description = "Type of the address", example = "Home")
    @NotBlank(message = "Type cannot be blank")
    private String type;

    @Schema(description = "First line of the address", example = "123 Main St")
    @NotBlank(message = "Address1 cannot be blank")
    private String address1;

    @Schema(description = "Second line of the address", example = "Apt 4B")
    private String address2;

    @Schema(description = "City of the address", example = "Springfield")
    @NotBlank(message = "City cannot be blank")
    private String city;

    @Schema(description = "State of the address as a 2-letter abbreviation", example = "IL")
    @Pattern(regexp = "^[A-Za-z]{2}$", message = "State should be a 2-letter abbreviation")
    private String state;

    @Schema(description = "Zip code of the address as a 5-digit number", example = "62704")
    @Pattern(regexp = "^\\d{5}$", message = "ZipCode should be a 5-digit number")
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    @Schema(description = "Customer associated with the address", accessMode = Schema.AccessMode.READ_ONLY)
    private Customer customer;
}
