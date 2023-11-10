package com.example.pharmacy.service.impl;

import com.example.pharmacy.model.Product;
import com.example.pharmacy.model.ShoppingCart;
import com.example.pharmacy.repository.ProductRepository;
import com.example.pharmacy.repository.ShoppingCartRepository;
import com.example.pharmacy.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    @Override
    public void clear(Long customerId) {
        ShoppingCart shoppingCart = findByCustomer(customerId);
        shoppingCart.setProducts(null);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart findByCustomer(Long customerId) {
        return shoppingCartRepository.findByCustomer_Id(customerId).orElseThrow(
                () -> new RuntimeException("Couldn't find shoppingCart by customerId: " + customerId)
        );
    }

    @Override
    public void addProductToShoppingCart(Long productId, Long customerId) {
        ShoppingCart cart = findByCustomer(customerId);
        cart.getProducts().add(productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Couldn't find product with id: " + productId)
        ));
        shoppingCartRepository.save(cart);
    }

    @Override
    public void deleteProductFromShoppingCart(Long productId, Long customerId) {
        ShoppingCart cart = findByCustomer(customerId);
        cart.getProducts().remove(productRepository.findById(productId).orElseThrow(
                () -> new RuntimeException("Couldn't find product with id: " + productId)
        ));
        shoppingCartRepository.save(cart);
    }
}
