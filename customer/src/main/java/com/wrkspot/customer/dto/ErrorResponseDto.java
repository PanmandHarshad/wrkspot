package com.wrkspot.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for error responses.
 * <p>
 * This class is used to structure the error response sent to the client
 * when an error occurs during the processing of a request.
 * It includes details such as the API path, error code, error message, and timestamp.
 * </p>
 */
@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {

    /**
     * The API path invoked by the client that resulted in the error.
     */
    @Schema(
            description = "API path invoked by client"
    )
    private String apiPath;

    /**
     * The HTTP status code representing the error that occurred.
     */
    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus errorCode;

    /**
     * A message describing the error that occurred.
     */
    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;

    /**
     * The timestamp indicating when the error occurred.
     */
    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime errorTime;

}
