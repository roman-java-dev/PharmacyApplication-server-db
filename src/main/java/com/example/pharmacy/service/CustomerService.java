package com.example.pharmacy.service;

import com.example.pharmacy.model.Customer;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer add(Customer customer);

    Customer findById(Long id);

    List<Customer> getAll();

    Customer findByPhoneNumber(Long phoneNumber);

    Optional<Customer> findByEmail(String email);

    Customer findByFirstNameAndLastName(String firstName, String lastName);

    Customer update(Long id, Customer customer);

    void delete(Long id);
}
