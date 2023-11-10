CREATE TABLE customers (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           first_name VARCHAR(255),
                           last_name VARCHAR(255),
                           phone_number BIGINT UNIQUE,
                           bonus INT
);