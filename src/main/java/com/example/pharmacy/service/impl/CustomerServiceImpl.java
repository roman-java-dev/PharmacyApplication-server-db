package com.example.pharmacy.service.impl;

import com.example.pharmacy.model.Customer;
import com.example.pharmacy.model.ShoppingCart;
import com.example.pharmacy.repository.CustomerRepository;
import com.example.pharmacy.repository.ShoppingCartRepository;
import com.example.pharmacy.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public Customer add(Customer customer) {
        try{
        Customer newCustomer = customerRepository.save(customer);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(newCustomer);
        shoppingCartRepository.save(shoppingCart);
        return newCustomer;
        }catch (DataIntegrityViolationException e){
            throw new RuntimeException(
                    "Duplicate entry " + customer.getPhoneNumber() + " for key 'customers.phone_number'");
        }
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.getReferenceById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByPhoneNumber(Long phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new RuntimeException("Couldn't find customer by phoneNumber: " + phoneNumber)
        );
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer findByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(
                () -> new RuntimeException("Couldn't find customer by firstName: " + firstName
                        + " and lastName: " + lastName)
        );
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer customerFromDB = customerRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Couldn't find customer by id: " + id)
        );
        customerFromDB.setFirstName(customer.getFirstName());
        customerFromDB.setLastName(customer.getLastName());
        customerFromDB.setPhoneNumber(customer.getPhoneNumber());
        return customerRepository.save(customerFromDB);
    }

    @Override
    public void delete(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByCustomer_Id(id).orElseThrow(
                () -> new RuntimeException("Couldn't delete customer by id: " + id)
        );
        shoppingCartRepository.delete(shoppingCart);
        customerRepository.deleteById(id);
    }
}
