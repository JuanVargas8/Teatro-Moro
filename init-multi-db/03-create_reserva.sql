-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c reserva

-- 1. ELIMINACIÓN (Orden jerárquico inverso)
DROP TABLE IF EXISTS Mapa_Asientos;

-- 2. TABLAS MAESTRAS
CREATE TABLE Mapa_Asientos (
    ID SERIAL PRIMARY KEY,
    ID_Sala INT,
    Fila CHAR(1),
    Numero INT,
    Coordenada_X INT,
    Coordenada_Y INT
);

INSERT INTO Mapa_Asientos (ID_Sala, Fila, Numero, Coordenada_X, Coordenada_Y) VALUES 
(1, 'A', 1, 10, 10), (1, 'A', 2, 20, 10), (1, 'A', 3, 30, 10);