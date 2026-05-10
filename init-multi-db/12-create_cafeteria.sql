-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
<<<<<<< HEAD
\c cafeteria
=======
--\c cafeteria
>>>>>>> f2a52777114bd406ec5049a084b553c4601114be

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Productos_Menu;


-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Productos_Menu (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Precio DECIMAL(10,2),
    Stock_Actual INT
);

INSERT INTO Productos_Menu (Nombre, Precio, Stock_Actual) VALUES 
('Vino Tinto Copa', 4500, 50),
('Palomitas Grandes', 3500, 100),
('Café Expreso', 2500, 200);