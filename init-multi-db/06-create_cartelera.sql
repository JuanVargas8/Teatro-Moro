-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
\c cartelera

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Planes_Abono;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Planes_Abono (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Precio DECIMAL(10,2),
    Beneficios TEXT
);

INSERT INTO Planes_Abono (Nombre, Precio, Beneficios) VALUES 
('Plan Platino', 100000, 'Acceso total y 20% dcto en bar'),
('Plan Temporada', 45000, 'Entrada a todas las obras del mes'),
('Abono Joven', 20000, '50% dcto en funciones de lunes a jueves');