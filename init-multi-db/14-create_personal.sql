-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c personal

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Personal;
-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Personal (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Especialidad VARCHAR(50),
    Tipo_Contrato VARCHAR(50)
);

INSERT INTO Personal (Nombre, Especialidad, Tipo_Contrato) VALUES 
('Roberto Lagos', 'Actor', 'Por Obra'),
('Lucía Méndez', 'Técnico de Iluminación', 'Planta'),
('Carlos Ruiz', 'Director Escénico', 'Honorarios');