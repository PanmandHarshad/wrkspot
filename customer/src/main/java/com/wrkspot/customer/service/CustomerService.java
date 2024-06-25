package com.wrkspot.customer.service;

import com.wrkspot.customer.entity.Customer;
import com.wrkspot.customer.exception.ResourceNotFoundException;
import com.wrkspot.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that handles business logic related to customers.
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Saves a list of customers in the database.
     *
     * @param customers The list of customers to be saved.
     * @return The list of saved customers.
     */
    public List<Customer> createCustomers(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    /**
     * Fetches customers based on optional name, city, and state filters.
     *
     * @param name  the optional name filter (partially matching)
     * @param city  the optional city filter
     * @param state the optional state filter
     * @return a list of customers matching the provided filters
     * @throws ResourceNotFoundException if no customers are found matching the filters
     */
    public List<Customer> getCustomersWithFilter(String name, String city, String state) {
        boolean cityAndStateFilter = city != null && !city.isEmpty() && state != null && !state.isEmpty();
        List<Customer> customerList;
        if (name != null && !name.isEmpty()) {
            if (cityAndStateFilter) {
                customerList = customerRepository.findByFirstNameContainingIgnoreCaseAndAddressesCityAndAddressesState(name, city, state);
            } else if (city != null && !city.isEmpty()) {
                customerList = customerRepository.findByFirstNameContainingIgnoreCaseAndAddressesCity(name, city);
            } else if (state != null && !state.isEmpty()) {
                customerList = customerRepository.findByFirstNameContainingIgnoreCaseAndAddressesState(name, state);
            } else {
                customerList = customerRepository.findByFirstNameContainingIgnoreCase(name);
            }
        } else if (cityAndStateFilter) {
            customerList = customerRepository.findByAddressesCityAndAddressesState(city, state);
        } else if (city != null && !city.isEmpty()) {
            customerList = customerRepository.findByAddressesCity(city);
        } else if (state != null && !state.isEmpty()) {
            customerList = customerRepository.findByAddressesState(state);
        } else {
            customerList = customerRepository.findAll();
        }

        if (customerList == null || customerList.isEmpty()) {
            throw new ResourceNotFoundException("Customer");
        }
        return customerList;
    }
}
