package com.example.pharmacy.service.impl;

import com.example.pharmacy.exception.AuthenticationException;
import com.example.pharmacy.model.Customer;
import com.example.pharmacy.service.AuthenticationService;
import com.example.pharmacy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final CustomerService service;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Customer register(String firstName, String lastName, Long phoneNumber, String email, String password) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNumber(phoneNumber);
        customer.setPassword(passwordEncoder.encode(password));
        customer.setBonus(0);
        customer.setRole(Customer.Role.CUSTOMER);
        customer = service.add(customer);
        return customer;
    }

    @Override
    public Customer login(String email, String password) throws AuthenticationException {
        Optional<Customer> customer = service.findByEmail(email);
        if (customer.isEmpty() || !passwordEncoder.matches(password, customer.get().getPassword())) {
            throw new AuthenticationException("Invalid email or password");
        }
        return customer.get();
    }
}
