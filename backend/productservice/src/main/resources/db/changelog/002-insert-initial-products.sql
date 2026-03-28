INSERT INTO products (name, price, stock_quantity, expiry_date) VALUES
('iPhone 15 Pro', 999.99, 50, '2025-12-31'),
('Samsung Galaxy S24', 899.99, 75, '2025-11-30'),
('MacBook Pro 16"', 2499.99, 25, '2026-06-30'),
('Dell XPS 13', 1299.99, 40, '2025-10-15'),
('Sony WH-1000XM5', 349.99, 100, '2026-03-31'),
('Nintendo Switch OLED', 349.99, 60, '2026-01-31'),
('iPad Pro 12.9"', 1099.99, 35, '2025-09-30'),
('Google Pixel 8', 699.99, 80, '2025-08-31'),
('AirPods Pro', 249.99, 120, '2026-05-31'),
('Apple Watch Series 9', 399.99, 90, '2025-12-31');

--rollback DELETE FROM products WHERE name IN ('iPhone 15 Pro', 'Samsung Galaxy S24', 'MacBook Pro 16"', 'Dell XPS 13', 'Sony WH-1000XM5', 'Nintendo Switch OLED', 'iPad Pro 12.9"', 'Google Pixel 8', 'AirPods Pro', 'Apple Watch Series 9');