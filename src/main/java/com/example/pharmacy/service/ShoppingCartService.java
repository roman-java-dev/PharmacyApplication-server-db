package com.example.pharmacy.service;

import com.example.pharmacy.model.Customer;
import com.example.pharmacy.model.ShoppingCart;

public interface ShoppingCartService {
    void clear(Long customerId);

    ShoppingCart findByCustomer(Long customerId);

    void addProductToShoppingCart(Long productId, Long customerId);

    void deleteProductFromShoppingCart(Long productId, Long customerId);
}
