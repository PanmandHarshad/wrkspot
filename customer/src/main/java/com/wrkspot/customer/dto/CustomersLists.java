package com.wrkspot.customer.dto;

import com.wrkspot.customer.entity.Customer;
import lombok.Data;

import java.util.List;

@Data
public class CustomersLists {
    private List<Customer> listA;
    private List<Customer> listB;
}
