-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c ticketing;

-- 1. ELIMINACIÓN (Orden jerárquico inverso)
DROP TABLE IF EXISTS Historial_Emisiones;
DROP TABLE IF EXISTS Tickets;
DROP TABLE IF EXISTS Tipos_Entrada;

CREATE TABLE Tipos_Entrada (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50),
    descuento NUMERIC(5,2)
);

CREATE TABLE Tickets (
    id SERIAL PRIMARY KEY,
    id_funcion INT,
    id_usuario INT,
    id_tipo_entrada INT,
    precio_final NUMERIC(10,2),
    FOREIGN KEY (id_tipo_entrada) REFERENCES Tipos_Entrada(id)
);

CREATE TABLE Historial_Emisiones (
    id SERIAL PRIMARY KEY,
    id_ticket INT,
    fecha_emision TIMESTAMP,
    canal_venta VARCHAR(20),
    FOREIGN KEY (id_ticket) REFERENCES Tickets(id)
);

INSERT INTO Tipos_Entrada VALUES
(DEFAULT,'General',0),
(DEFAULT,'VIP',10);

INSERT INTO Tickets VALUES
(DEFAULT,1,101,1,20000),
(DEFAULT,1,102,2,30000);

INSERT INTO Historial_Emisiones VALUES
(DEFAULT,1,NOW(),'Web'),
(DEFAULT,2,NOW(),'Taquilla');