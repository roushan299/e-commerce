-- Insert data into category table
INSERT INTO category (id, description, name) VALUES
(1, 'Electronics and gadgets', 'Electronics'),
(2, 'Home appliances', 'Appliances'),
(3, 'Books and literature', 'Books'),
(4, 'Clothing and accessories', 'Fashion'),
(5, 'Health and beauty products', 'Health');

-- Insert data into product table
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES
(1, 'Latest smartphone with advanced features', 'Smartphone X', 150, 799.99, 1),
(2, 'Energy-efficient washing machine', 'Washing Machine Y', 75, 499.99, 2),
(3, 'Bestselling novel of the year', 'Novel Z', 200, 19.99, 3),
(4, 'Stylish leather jacket', 'Leather Jacket A', 50, 129.99, 4),
(5, 'Organic skincare set', 'Skincare Set B', 100, 39.99, 5);
