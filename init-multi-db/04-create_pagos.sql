-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
\c pagos

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Transacciones;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Transacciones (
    ID SERIAL PRIMARY KEY,
    ID_Pedido INT,
    Monto DECIMAL(10,2),
    Metodo_Pago VARCHAR(50),
    Estado VARCHAR(20) -- Pendiente, Aprobado, Fallido
);

INSERT INTO Transacciones (ID_Pedido, Monto, Metodo_Pago, Estado) VALUES 
(501, 50000, 'Tarjeta_Debito', 'Aprobado'), 
(502, 25000, 'WebPay', 'Pendiente'),
(503, 15000, 'Transferencia', 'Aprobado');