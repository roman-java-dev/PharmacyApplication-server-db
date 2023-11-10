package com.example.pharmacy.service.impl;

import com.example.pharmacy.model.Customer;
import com.example.pharmacy.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.CustomerServiceThrift;
import communication.CustomerThrift;
import communication.InvalidOperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceThriftImpl implements CustomerServiceThrift.Iface {
    private final CustomerService service;
    private final ObjectMapper objectMapper;

    @Override
    public CustomerThrift add(CustomerThrift customerThrift) throws InvalidOperationException {
        try {
            return objectMapper.convertValue(
                    service.add(objectMapper.convertValue(customerThrift, Customer.class)),
                    CustomerThrift.class);
        } catch (RuntimeException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public List<CustomerThrift> getAll() {
        return service.getAll().stream()
                .map(customer -> objectMapper.convertValue(customer, CustomerThrift.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerThrift findByPhoneNumber(long phoneNumber) throws InvalidOperationException {
        try {
            return objectMapper.convertValue(service.findByPhoneNumber(phoneNumber),
                    CustomerThrift.class);
        } catch (RuntimeException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public CustomerThrift findByFirstNameAndLastName(String firstName, String lastName)
            throws InvalidOperationException{
        try {
            return objectMapper.convertValue(service.findByFirstNameAndLastName(firstName, lastName),
                    CustomerThrift.class);
        } catch (RuntimeException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public CustomerThrift findById(long id) throws InvalidOperationException {
        try {
            return objectMapper.convertValue(service.findById(id), CustomerThrift.class);
        } catch (RuntimeException e) {
            throw new InvalidOperationException("Couldn't find customer by id: " + id, HttpStatus.NOT_FOUND.value());
        }

    }

    @Override
    public CustomerThrift update(long id, CustomerThrift customerThrift) throws InvalidOperationException{
        try {
            return objectMapper.convertValue(
                    service.update(id, objectMapper.convertValue(customerThrift, Customer.class)),
                    CustomerThrift.class);
        } catch (RuntimeException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public void delete(long id) throws InvalidOperationException {
        try {
            service.delete(id);
        } catch (RuntimeException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }
}
