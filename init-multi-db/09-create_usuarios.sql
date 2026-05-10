-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
<<<<<<< HEAD
\c usuarios
=======
--\c usuarios
>>>>>>> f2a52777114bd406ec5049a084b553c4601114be

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Usuarios;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Usuarios (
    ID SERIAL PRIMARY KEY,
    Email VARCHAR(150) UNIQUE,
    Password_Hash VARCHAR(255),
    Nombre VARCHAR(100),
    Fecha_Registro DATE DEFAULT CURRENT_DATE
);

INSERT INTO Usuarios (Email, Password_Hash, Nombre) VALUES 
('admin@teatromoro.cl', '$2b$12$hash', 'Administrador General'),
('juan.perez@email.com', '$2b$12$hash2', 'Juan Pérez'),
('maria.g@email.com', '$2b$12$hash3', 'María García');