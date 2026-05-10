-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
<<<<<<< HEAD
\c gestion
=======
--\c gestion
>>>>>>> f2a52777114bd406ec5049a084b553c4601114be

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Salas;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Salas (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(50),
    Capacidad_Total INT,
    Descripcion_Tecnica TEXT
);

INSERT INTO Salas (Nombre, Capacidad_Total, Descripcion_Tecnica) VALUES 
('Gran Teatro Principal', 500, 'Sonido Dolby Atmos, Escenario giratorio'),
('Sala de Cámara', 120, 'Acústica natural, Iluminación LED'),
('Sala Experimental', 80, 'Configuración flexible de asientos');