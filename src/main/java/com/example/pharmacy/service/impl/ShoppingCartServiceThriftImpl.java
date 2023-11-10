package com.example.pharmacy.service.impl;

import com.example.pharmacy.mapper.CustomMapper;
import com.example.pharmacy.service.ShoppingCartService;
import communication.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceThriftImpl implements ShoppingCartServiceThrift.Iface {
    private final CustomMapper mapper;
    private final ShoppingCartService cartService;

    @Override
    public ShoppingCartThrift findByCustomer(long customerThriftId) throws InvalidOperationException {
        try {
            return mapper.mapToShoppingCartThrift(cartService.findByCustomer(customerThriftId));
        }catch (RuntimeException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public void addProductToShoppingCart(long productThriftId, long customerThriftId) throws InvalidOperationException {
        try {
            cartService.addProductToShoppingCart(productThriftId, customerThriftId);
        }catch (RuntimeException e) {
            throw new InvalidOperationException(
                    "Can`t add product with id " + productThriftId + ". Exception : "
                            + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @Override
    public void deleteProductFromShoppingCart(long productThriftId, long customerThriftId) throws InvalidOperationException {
        try {
            cartService.deleteProductFromShoppingCart(productThriftId, customerThriftId);
        }catch (RuntimeException e) {
            throw new InvalidOperationException(
                    "Can`t delete product with id " + productThriftId + ". Exception : "
                            + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public void clear(long customerThriftId) throws InvalidOperationException {
        try {
            cartService.clear(customerThriftId);
        }catch (RuntimeException e) {
            throw new InvalidOperationException(
                    "Can`t clear shoppingCart with customerID " + customerThriftId + ". Exception : "
                            + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
