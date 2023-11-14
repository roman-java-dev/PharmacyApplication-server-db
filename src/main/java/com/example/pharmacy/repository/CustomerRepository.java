package com.example.pharmacy.repository;

import com.example.pharmacy.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByPhoneNumber(Long phoneNumber);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
