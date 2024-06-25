package com.wrkspot.customer.controller;

import com.wrkspot.customer.entity.Customer;
import com.wrkspot.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddCustomerSuccess() {
        Customer customer = new Customer();
        customer.setCustomerId("1L");
        customer.setFirstName("Test Customer");

        List<Customer> customerList = List.of(customer);
        when(customerService.createCustomers(customerList)).thenReturn(customerList);

        ResponseEntity<List<Customer>> response = customerController.createMultipleCustomers(customerList);

        assertNotNull(response.getBody());
        assertEquals("Test Customer", response.getBody().get(0).getFirstName());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
