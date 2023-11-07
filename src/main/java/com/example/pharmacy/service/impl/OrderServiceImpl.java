package com.example.pharmacy.service.impl;

import com.example.pharmacy.model.Customer;
import com.example.pharmacy.model.Order;
import com.example.pharmacy.model.ShoppingCart;
import com.example.pharmacy.repository.OrderRepository;
import com.example.pharmacy.service.OrderService;
import com.example.pharmacy.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setProducts(shoppingCart.getProducts());
        order.setCustomer(shoppingCart.getCustomer());
        orderRepository.save(order);
        shoppingCartService.clear(shoppingCart);
        return order;
    }

    @Override
    public List<Order> getOrdersHistory(Customer customer) {
        return orderRepository.findAllByCustomer(customer);
    }
}
