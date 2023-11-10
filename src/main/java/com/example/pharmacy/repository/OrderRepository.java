package com.example.pharmacy.repository;

import com.example.pharmacy.model.Customer;
import com.example.pharmacy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer_Id(Long customerId);
}
