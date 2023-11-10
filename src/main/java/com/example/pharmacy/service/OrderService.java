package com.example.pharmacy.service;

import com.example.pharmacy.model.Customer;
import com.example.pharmacy.model.Order;
import com.example.pharmacy.model.ShoppingCart;

import java.util.List;

public interface OrderService {
    Order completeOrder(Long customerId);

    List<Order> getOrdersHistory(Long customerId);
}
