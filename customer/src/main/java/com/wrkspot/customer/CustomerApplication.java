package com.wrkspot.customer;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Customer microservice REST API Documentation",
                description = "WrkSpot customer microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Harshad Panmand",
                        email = "Harshad.Panmand@gmail.com",
                        url = "https://www.wrkspot.com/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.wrkspot.com/"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "WrkSpot Customer microservice REST API Documentation",
                url = "https://www.wrkspot.com/swagger-ui.html"
        )
)
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

}
