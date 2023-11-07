package com.example.pharmacy.service;

import com.example.pharmacy.model.Product;

import java.util.List;

public interface ProductService {
    Product add(Product product);

    Product findByName(String name);

    List<Product> getAll();

    Product update(Long id, Product product);

    void delete(Long id);
}
