package com.wrkspot.customer.util;

import com.wrkspot.customer.entity.Customer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for performing operations on lists of customers.
 */
public class CustomerUtils {

    /**
     * Returns a list of customers that are only present in list A and not in list B.
     *
     * @param listA List of customers A
     * @param listB List of customers B
     * @return List of customers only in list A
     * @implNote This method uses the customer ID as the criteria for filtering.
     */
    public static List<Customer> customersOnlyInA(List<Customer> listA, List<Customer> listB) {
        return listA.stream()
                .filter(customerA -> listB.stream().noneMatch(customerB -> customerB.getCustomerId().equals(customerA.getCustomerId())))
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of customers that are only present in list B and not in list A.
     *
     * @param listA List of customers A
     * @param listB List of customers B
     * @return List of customers only in list B
     * @implNote This method uses the customer ID as the criteria for filtering.
     */
    public static List<Customer> customersOnlyInB(List<Customer> listA, List<Customer> listB) {
        return listB.stream()
                .filter(customerB -> listA.stream().noneMatch(customerA -> customerA.getCustomerId().equals(customerB.getCustomerId())))
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of customers that are present in both list A and list B.
     *
     * @param listA List of customers A
     * @param listB List of customers B
     * @return List of customers present in both list A and list B
     * @implNote This method uses the customer ID as the criteria for filtering.
     */
    public static List<Customer> customersInBothAAndB(List<Customer> listA, List<Customer> listB) {
        return listA.stream()
                .filter(customerA -> listB.stream().anyMatch(customerB -> customerB.getCustomerId().equals(customerA.getCustomerId())))
                .collect(Collectors.toList());
    }
}
