package com.example.pharmacy.service.impl;

import com.example.pharmacy.model.Product;
import com.example.pharmacy.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import communication.InvalidOperationException;
import communication.ProductServiceThrift;
import communication.ProductThrift;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceThriftImpl implements ProductServiceThrift.Iface {
    private final ProductService service;
    private final ObjectMapper objectMapper;

    @Override
    public ProductThrift add(ProductThrift productThrift) throws InvalidOperationException {
        try {
            return objectMapper.convertValue(
                    service.add(objectMapper.convertValue(productThrift, Product.class)),
                    ProductThrift.class);
        } catch (RuntimeException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public ProductThrift findByName(String name) throws InvalidOperationException {
        try {
            return objectMapper.convertValue(service.findByName(name), ProductThrift.class);
        } catch (RuntimeException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public List<ProductThrift> getAll() throws InvalidOperationException {
        return service.getAll().stream()
                .map(product -> objectMapper.convertValue(product, ProductThrift.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductThrift update(long id, ProductThrift productThrift) throws InvalidOperationException {
        try {
            return objectMapper.convertValue(
                    service.update(id, objectMapper.convertValue(productThrift, Product.class)),
                    ProductThrift.class);
        } catch (RuntimeException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @Override
    public void delete(long id) throws InvalidOperationException {
        try {
            service.delete(id);
        } catch (RuntimeException e) {
            throw new InvalidOperationException(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }
}
