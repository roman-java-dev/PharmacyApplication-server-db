CREATE TABLE shopping_carts (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                customer_id INT,
                                FOREIGN KEY (customer_id) REFERENCES customers (id)
);