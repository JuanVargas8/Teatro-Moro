-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c informes

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Metricas_Ocupacion;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Metricas_Ocupacion (
    ID SERIAL PRIMARY KEY,
    ID_Funcion INT,
    Porcentaje_Llenado DECIMAL(5,2)
);

INSERT INTO Metricas_Ocupacion (ID_Funcion, Porcentaje_Llenado) VALUES 
(1, 85.50), (2, 100.00), (3, 42.10);