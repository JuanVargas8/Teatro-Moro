-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
\c marketing

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Cupones;
-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Cupones (
    ID SERIAL PRIMARY KEY,
    Codigo VARCHAR(20) UNIQUE,
    Porcentaje_Descuento DECIMAL(5,2),
    Fecha_Expiracion DATE,
    Limite_Uso INT
);

INSERT INTO Cupones (Codigo, Porcentaje_Descuento, Fecha_Expiracion, Limite_Uso) VALUES 
('BIENVENIDA24', 10.00, '2024-12-31', 500),
('DIADELTEATRO', 50.00, '2024-05-11', 100),
('PROMOVERANO', 20.00, '2024-02-28', 200);