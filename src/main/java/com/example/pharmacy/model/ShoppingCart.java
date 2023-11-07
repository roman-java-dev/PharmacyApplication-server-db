package com.example.pharmacy.model;

import lombok.Data;
import java.util.List;
import javax.persistence.*;

@Data
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Product> products;
    @OneToOne
    private Customer customer;
}
