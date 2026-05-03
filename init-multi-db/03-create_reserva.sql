-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c reserva

-- 1. ELIMINACIÓN (Orden jerárquico inverso)
DROP TABLE IF EXISTS Bloqueos_Temporales;
DROP TABLE IF EXISTS Estado_Asientos;
DROP TABLE IF EXISTS Mapa_Asientos;

CREATE TABLE Mapa_Asientos (
    id SERIAL PRIMARY KEY,
    id_sala INT,
    fila VARCHAR(5),
    numero INT,
    coord_x INT
);

CREATE TABLE Estado_Asientos (
    id SERIAL PRIMARY KEY,
    id_asiento INT,
    id_funcion INT,
    estado VARCHAR(20),
    FOREIGN KEY (id_asiento) REFERENCES Mapa_Asientos(id)
);

CREATE TABLE Bloqueos_Temporales (
    id SERIAL PRIMARY KEY,
    id_asiento INT,
    id_sesion_usuario VARCHAR(50),
    expiracion TIMESTAMP,
    FOREIGN KEY (id_asiento) REFERENCES Mapa_Asientos(id)
);

INSERT INTO Mapa_Asientos VALUES
(DEFAULT,1,'A',1,10),
(DEFAULT,1,'A',2,20);

INSERT INTO Estado_Asientos VALUES
(DEFAULT,1,1,'Libre'),
(DEFAULT,2,1,'Reservado');

INSERT INTO Bloqueos_Temporales VALUES
(DEFAULT,2,'session123',NOW());