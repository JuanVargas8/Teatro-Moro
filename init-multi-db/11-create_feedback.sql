-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c feedback

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Resenas;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Resenas (
    ID SERIAL PRIMARY KEY,
    ID_Usuario INT REFERENCES Usuarios(ID),
    ID_Obra INT REFERENCES Obras(ID),
    Calificacion INT CHECK (Calificacion BETWEEN 1 AND 5),
    Comentario TEXT
);

INSERT INTO Resenas (ID_Usuario, ID_Obra, Calificacion, Comentario) VALUES 
(2, 1, 5, 'Excelente puesta en escena.'),
(3, 2, 4, 'Muy buen musical, aunque un poco largo.'),
(2, 3, 3, 'Interesante pero difícil de seguir.');