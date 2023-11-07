package com.example.pharmacy.service;

import com.example.pharmacy.model.Customer;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface CustomerService {
    Customer add(Customer customer);

    List<Customer> getAll();

    Customer findByPhoneNumber(Long phoneNumber);

    Customer findByFirstNameAndLastName(String firstName, String lastName);

    Customer update(Long id, Customer customer);

    void delete(Long id);
}
