-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c funciones

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Funciones;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Funciones (
    ID SERIAL PRIMARY KEY,
    ID_Obra INT REFERENCES Obras(ID),
    ID_Sala INT,
    Fecha_Hora TIMESTAMP,
    Precio_Base DECIMAL(10,2)
);

INSERT INTO Funciones (ID_Obra, ID_Sala, Fecha_Hora, Precio_Base) VALUES 
(1, 1, '2024-06-01 20:00:00', 15000),
(2, 2, '2024-06-01 18:00:00', 35000),
(3, 1, '2024-06-02 19:30:00', 12000);