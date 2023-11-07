CREATE TABLE orders (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        order_date TIMESTAMP,
                        customer_id INT,
                        FOREIGN KEY (customer_id) REFERENCES customers (id)
);