package com.example.pharmacy.mapper;

import com.example.pharmacy.model.Order;
import com.example.pharmacy.model.ShoppingCart;
import communication.OrderThrift;
import communication.ShoppingCartThrift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomMapper {

    @Mapping(target = "productsThrifts", source = "products")
    @Mapping(target = "customerThrift", source = "customer")
    OrderThrift mapToOrderThrift(Order order);

    @Mapping(target = "productsThrifts", source = "products")
    @Mapping(target = "customerThrift", source = "customer")
    ShoppingCartThrift mapToShoppingCartThrift(ShoppingCart shoppingCart);
}
