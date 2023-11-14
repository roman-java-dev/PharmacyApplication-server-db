package com.example.pharmacy.service;

import com.example.pharmacy.exception.AuthenticationException;
import com.example.pharmacy.model.Customer;

public interface AuthenticationService {
    Customer register(String firstName, String lastName, Long phoneNumber, String email, String password);

    Customer login(String email, String password) throws AuthenticationException;
}