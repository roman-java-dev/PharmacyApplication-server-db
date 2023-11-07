package com.example.pharmacy.service;

import com.example.pharmacy.model.Customer;
import com.example.pharmacy.model.Order;
import com.example.pharmacy.model.ShoppingCart;

import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(Customer customer);
}
