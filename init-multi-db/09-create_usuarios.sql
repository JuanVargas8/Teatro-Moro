-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c usuarios

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Usuario;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    fecha_registro DATE
);

INSERT INTO Usuario (email, password, nombre, fecha_registro) VALUES 
('admin@teatro.com', '$2a$10$8.UnVuG9HHgffUDAlk8q6Ou5f2HW1Y...', 'Administrador', '2026-01-10'),
('juan.perez@email.com', '$2a$10$R9h/lIPzHZLhJ1.VBRT.7Ou76W...', 'Juan Pérez', '2026-03-15'),
('maria.garcia@email.com', '$2a$10$B7N3156S.S.O.K2u92S.OuV...', 'Maria García', '2026-05-11');
