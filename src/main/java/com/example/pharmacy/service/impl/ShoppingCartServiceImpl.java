package com.example.pharmacy.service.impl;

import com.example.pharmacy.model.ShoppingCart;
import com.example.pharmacy.repository.ShoppingCartRepository;
import com.example.pharmacy.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.setProducts(null);
        shoppingCartRepository.save(shoppingCart);
    }
}
