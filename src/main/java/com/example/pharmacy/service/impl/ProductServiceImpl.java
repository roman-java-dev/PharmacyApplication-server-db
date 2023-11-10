package com.example.pharmacy.service.impl;

import com.example.pharmacy.model.Product;
import com.example.pharmacy.repository.ProductRepository;
import com.example.pharmacy.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product add(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findByName(name).orElseThrow(
                () -> new RuntimeException("Couldn't find product by name: " + name)
        );
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Long id, Product product) {
        Product productFromDB = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Couldn't find product by id: " + id)
        );
        productFromDB.setName(product.getName());
        productFromDB.setPrice(product.getPrice());
        productFromDB.setQuantity(product.getQuantity());
        productFromDB.setDescription(product.getDescription());
        return productRepository.save(productFromDB);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
