-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
\c merchandising

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Inventario_Items;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Inventario_Items (
    ID SERIAL PRIMARY KEY,
    ID_Obra_Relacionada INT REFERENCES Obras(ID),
    Nombre VARCHAR(100),
    SKU VARCHAR(50),
    Precio DECIMAL(10,2)
);

INSERT INTO Inventario_Items (ID_Obra_Relacionada, Nombre, SKU, Precio) VALUES 
(1, 'Libro Hamlet - Edición Teatro', 'BK-HAM-01', 12000),
(2, 'Peluche Gato Cats', 'TOY-CAT-02', 8000),
(NULL, 'Taza Logo Teatro Moro', 'MER-GEN-01', 5000);