CREATE TABLE shopping_carts_products (
                                shopping_cart_id INT,
                                product_id INT,
                                PRIMARY KEY (shopping_cart_id, product_id),
                                FOREIGN KEY (shopping_cart_id) REFERENCES shopping_carts (id),
                                FOREIGN KEY (product_id) REFERENCES products (id)
);