package com.wrkspot.customer.service;

import com.wrkspot.customer.entity.Address;
import com.wrkspot.customer.entity.Customer;
import com.wrkspot.customer.exception.ResourceNotFoundException;
import com.wrkspot.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

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

        when(customerRepository.saveAll(customerList)).thenReturn(customerList);

        List<Customer> savedCustomer = customerService.createCustomers(customerList);

        assertNotNull(savedCustomer);
        assertEquals("Test Customer", savedCustomer.get(0).getFirstName());
        verify(customerRepository, times(1)).saveAll(customerList);
    }

    @Test
    void testGetCustomersWithFilterNoParams() {
        Customer customer = new Customer();
        customer.setCustomerId("1L");
        customer.setFirstName("Test Customer");

        List<Customer> customerList = List.of(customer);
        when(customerRepository.findAll()).thenReturn(customerList);
        when(customerService.getCustomersWithFilter(null, null, null)).thenReturn(customerList);

        List<Customer> resultCustomerList = customerService.getCustomersWithFilter(null, null, null);

        assertFalse(resultCustomerList.isEmpty());
        assertEquals("Test Customer", resultCustomerList.get(0).getFirstName());
    }

    @Test
    void testGetCustomersWithFilterWithParams() {
        Customer customer = new Customer();
        customer.setCustomerId("1L");
        customer.setFirstName("Test Customer");
        Address address = new Address();
        address.setCity("Test City");
        address.setState("Test State");
        customer.setAddresses(List.of(address));

        List<Customer> customerList = List.of(customer);

        when(customerRepository.findByFirstNameContainingIgnoreCaseAndAddressesCityAndAddressesState("Test Customer", "Test City", "Test State"))
                .thenReturn(customerList);

        when(customerService.getCustomersWithFilter("Test Customer", "Test City", "Test State"))
                .thenReturn(customerList);

        List<Customer> resultCustomerlist = customerService.getCustomersWithFilter("Test Customer", "Test City", "Test State");

        assertFalse(resultCustomerlist.isEmpty());
        assertEquals("Test Customer", resultCustomerlist.get(0).getFirstName());
    }

    @Test
    void testGetCustomerByFirstNameNotFound() {
        when(customerRepository.findByFirstNameContainingIgnoreCase("Test Customer")).thenReturn(List.of());

        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.getCustomersWithFilter("Test Customer", null, null);
        });

        verify(customerRepository, times(1)).findByFirstNameContainingIgnoreCase("Test Customer");
    }

}
