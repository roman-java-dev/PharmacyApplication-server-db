package com.example.pharmacy.service.impl;

import com.example.pharmacy.exception.AuthenticationException;
import com.example.pharmacy.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.AuthenticationServiceThrift;
import communication.CustomerThrift;
import communication.InvalidOperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceThriftImpl implements AuthenticationServiceThrift.Iface {
    private final AuthenticationService service;
    private final ObjectMapper mapper;
    @Override
    public CustomerThrift login(String email, String password) throws InvalidOperationException {
        try {
            return mapper.convertValue(service.login(email, password),
                    CustomerThrift.class);
        } catch (AuthenticationException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public CustomerThrift register(String firstName, String lastName,
                                   long phoneNumber, String email, String password) throws InvalidOperationException {
        try {
            return mapper.convertValue(service.register(firstName, lastName, phoneNumber, email, password),
                    CustomerThrift.class);
        } catch (RuntimeException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }
}
