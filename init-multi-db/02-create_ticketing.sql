-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
\c ticketing;

-- 1. ELIMINACIÓN (Orden jerárquico inverso)
DROP TABLE IF EXISTS Tipos_Entrada;
DROP TABLE IF EXISTS Tickets;
-- 2. TABLAS MAESTRAS
CREATE TABLE Tipos_Entrada (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(50),
    Descuento_Asociado DECIMAL(5,2)
);

CREATE TABLE Tickets (
    ID SERIAL PRIMARY KEY,
    ID_Funcion INT,
    ID_Usuario INT,
    Codigo_QR VARCHAR(255),
    Precio_Final DECIMAL(10,2)
);

INSERT INTO Tipos_Entrada (Nombre, Descuento_Asociado) VALUES 
('General', 0.00), ('VIP', 0.00), ('Estudiante', 15.00);

INSERT INTO Tickets (ID_Funcion, ID_Usuario, Codigo_QR, Precio_Final) VALUES 
(1, 10, 'QR-7721', 25000), (1, 11, 'QR-7722', 25000), (2, 12, 'QR-8810', 45000);