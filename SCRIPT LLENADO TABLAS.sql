create database if not exists javacoderproject;
use javacoderproject;
select * from clients;
select * from products;
select * from invoices;
select * from invoice_details;



-- LLENADO DE PRODUCTOS INICIAL;
INSERT INTO products (code, title, current_price, stock)
VALUES
('P001', 'Producto A', 19.99, 100),
('P002', 'Producto B', 29.99, 50),
('P003', 'Producto C', 15.49, 200),
('P004', 'Producto D', 99.99, 10),
('P005', 'Producto E', 49.99, 75),
('P006', 'Producto F', 9.99, 300),
('P007', 'Producto G', 39.99, 60),
('P008', 'Producto H', 24.99, 150),
('P009', 'Producto I', 59.99, 80),
('P010', 'Producto J', 69.99, 40),
('P011', 'Producto K', 19.99, 120),
('P012', 'Producto L', 89.99, 30),
('P013', 'Producto M', 39.99, 95),
('P014', 'Producto N', 79.99, 50),
('P015', 'Producto O', 29.99, 220),
('P016', 'Producto P', 59.99, 10),
('P017', 'Producto Q', 14.99, 150),
('P018', 'Producto R', 99.99, 5),
('P019', 'Producto S', 25.49, 90),
('P020', 'Producto T', 39.99, 70);


INSERT INTO clients (dni, first_name, last_name, address_street, postal_code, phone_number)
VALUES
('12345678', 'Juan', 'Pérez', 'Calle Falsa 123', '1234', '555-1234'),
('87654321', 'María', 'Gómez', 'Avenida Siempre Viva 742', '5678', '555-5678'),
('11223344', 'Carlos', 'Sánchez', 'Calle Mayor 456', '2345', '555-2345'),
('22334455', 'Laura', 'Martínez', 'Calle Luna 789', '3456', '555-3456'),
('33445566', 'José', 'Rodríguez', 'Calle Sol 101', '4567', '555-4567');
