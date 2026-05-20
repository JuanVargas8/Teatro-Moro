-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c ticketing;

-- ============================================================
-- 1. ELIMINACIÓN DE TABLAS (ORDEN DE DEPENDENCIA)
-- ============================================================
DROP TABLE IF EXISTS Historial_Emisiones;
DROP TABLE IF EXISTS Tickets;
DROP TABLE IF EXISTS Tipos_Entrada;

-- ============================================================
-- 2. TABLA: Tipos_Entrada
-- ============================================================
CREATE TABLE Tipos_Entrada (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descuento DECIMAL(10,2) NOT NULL
);

INSERT INTO Tipos_Entrada (nombre, descuento) VALUES 
('General', 0.00),
('Estudiante', 50.00),
('Adulto Mayor', 30.00);

-- ============================================================
-- 3. TABLA: Tickets
-- ============================================================
CREATE TABLE Tickets (
    id SERIAL PRIMARY KEY,
    id_funcion INTEGER NOT NULL,
    id_usuario INTEGER NOT NULL,
    precio_final DECIMAL(12,2) NOT NULL,
    id_tipo_entrada INTEGER NOT NULL,
    CONSTRAINT fk_tipo_entrada FOREIGN KEY (id_tipo_entrada) REFERENCES Tipos_Entrada(id)
);

INSERT INTO Tickets (id_funcion, id_usuario, precio_final, id_tipo_entrada) VALUES 
(1, 10, 15000.00, 1),
(1, 11, 7500.00, 2),
(2, 12, 10500.00, 3);

-- ============================================================
-- 4. TABLA: Historial_Emisiones
-- ============================================================
CREATE TABLE Historial_Emisiones (
    id SERIAL PRIMARY KEY,
    fecha_emision DATE NOT NULL,
    canal_venta VARCHAR(20) NOT NULL,
    id_ticket INTEGER NOT NULL,
    CONSTRAINT fk_ticket_emision FOREIGN KEY (id_ticket) REFERENCES Tickets(id)
);

INSERT INTO Historial_Emisiones (fecha_emision, canal_venta, id_ticket) VALUES 
('2026-05-11', 'WEB', 1),
('2026-05-11', 'TAQUILLA', 2),
('2026-05-11', 'APP', 3);
