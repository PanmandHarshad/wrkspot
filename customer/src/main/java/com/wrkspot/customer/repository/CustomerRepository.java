package com.wrkspot.customer.repository;

import com.wrkspot.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on Customer entities.
 * Extends JpaRepository to inherit basic database operations.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Finds customers by a combination of name, city, and state (ignores case).
     *
     * @param name  the name to search for (partially matching)
     * @param city  the city to search for
     * @param state the state to search for
     * @return a list of customers matching the criteria
     */
    List<Customer> findByFirstNameContainingIgnoreCaseAndAddressesCityAndAddressesState(String name, String city, String state);

    /**
     * Finds customers by a combination of name and city (ignores case).
     *
     * @param name the name to search for (partially matching)
     * @param city the city to search for
     * @return a list of customers matching the criteria
     */
    List<Customer> findByFirstNameContainingIgnoreCaseAndAddressesCity(String name, String city);

    /**
     * Finds customers by a combination of name and state (ignores case).
     *
     * @param name  the name to search for (partially matching)
     * @param state the state to search for
     * @return a list of customers matching the criteria
     */
    List<Customer> findByFirstNameContainingIgnoreCaseAndAddressesState(String name, String state);

    /**
     * Finds customers by name (ignores case).
     *
     * @param name the name to search for (partially matching)
     * @return a list of customers matching the criteria
     */
    List<Customer> findByFirstNameContainingIgnoreCase(String name);

    /**
     * Finds customers by city and state.
     *
     * @param city  the city to search for
     * @param state the state to search for
     * @return a list of customers matching the criteria
     */
    List<Customer> findByAddressesCityAndAddressesState(String city, String state);

    /**
     * Finds customers by city.
     *
     * @param city the city to search for
     * @return a list of customers matching the criteria
     */
    List<Customer> findByAddressesCity(String city);

    /**
     * Finds customers by state.
     *
     * @param state the state to search for
     * @return a list of customers matching the criteria
     */
    List<Customer> findByAddressesState(String state);

    /**
     * Finds all customers.
     *
     * @return a list of all customers
     */
    List<Customer> findAll();

    /**
     * Finds a customer by their customerId.
     *
     * @param customerId the customerId to search for
     * @return an Optional containing the Customer if found, or empty otherwise
     */
    Optional<Customer> findByCustomerId(String customerId);
}
