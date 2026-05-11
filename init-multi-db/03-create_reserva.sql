-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c reserva

-- ============================================================
-- 1. ELIMINACIÓN DE TABLAS (ORDEN DE DEPENDENCIA)
-- ============================================================
DROP TABLE IF EXISTS Bloqueos_Temporales;
DROP TABLE IF EXISTS Estado_Asientos;
DROP TABLE IF EXISTS Mapa_Asientos;

-- ============================================================
-- 2. TABLA: MAPA_ASIENTOS
-- ============================================================
CREATE TABLE Mapa_Asientos (
    id SERIAL PRIMARY KEY,
    id_sala INTEGER NOT NULL,
    fila VARCHAR(5) NOT NULL,
    numero INTEGER NOT NULL,
    coord_x INTEGER NOT NULL
);

INSERT INTO Mapa_Asientos (id_sala, fila, numero, coord_x) VALUES 
(1, 'A', 1, 100),
(1, 'A', 2, 120),
(2, 'VIP', 10, 500);

-- ============================================================
-- 3. TABLA: ESTADO_ASIENTOS
-- ============================================================
CREATE TABLE Estado_Asientos (
    id SERIAL PRIMARY KEY,
    id_funcion INTEGER NOT NULL,
    estado VARCHAR(20) NOT NULL,
    id_asiento INTEGER NOT NULL,
    CONSTRAINT fk_asiento_estado FOREIGN KEY (id_asiento) REFERENCES Mapa_Asientos(id)
);

INSERT INTO Estado_Asientos (id_funcion, estado, id_asiento) VALUES 
(1, 'OCUPADO', 1),
(1, 'DISPONIBLE', 2),
(2, 'RESERVADO', 3);

-- ============================================================
-- 4. TABLA: BLOQUEOS_TEMPORALES
-- ============================================================
CREATE TABLE Bloqueos_Temporales (
    id SERIAL PRIMARY KEY,
    id_sesion_usuario VARCHAR(50) NOT NULL,
    expiracion TIMESTAMP NOT NULL,
    id_asiento INTEGER NOT NULL,
    CONSTRAINT fk_asiento_bloqueo FOREIGN KEY (id_asiento) REFERENCES Mapa_Asientos(id)
);

INSERT INTO Bloqueos_Temporales (id_sesion_usuario, expiracion, id_asiento) VALUES 
('SESS-123', '2026-05-11 20:30:00', 2),
('SESS-456', '2026-05-11 19:45:00', 3);