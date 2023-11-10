CREATE TABLE order_products (
                                order_id INT,
                                product_id INT,
                                PRIMARY KEY (order_id, product_id),
                                FOREIGN KEY (order_id) REFERENCES orders (id),
                                FOREIGN KEY (product_id) REFERENCES products (id)
);