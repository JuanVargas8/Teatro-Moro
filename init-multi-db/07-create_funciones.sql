--\c funciones

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Turnos_Funcion;
DROP TABLE IF EXISTS Funciones;
DROP TABLE IF EXISTS Excepciones_Horario;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Excepciones_Horario (
    ID SERIAL PRIMARY KEY,
    Fecha DATE,
    Motivo VARCHAR(100)
);

INSERT INTO Excepciones_Horario (Fecha, Motivo) VALUES 
('2026-05-01', 'Feriado: Día del Trabajador'),
('2026-09-18', 'Feriado: Fiestas Patrias');

CREATE TABLE Funciones (
    ID SERIAL PRIMARY KEY,
    ID_Obra INT, -- Llave foránea lógica hacia Obras(ID) en Cartelera
    ID_Sala INT, -- Llave foránea lógica hacia Salas(ID) en Gestión
    Fecha_Hora TIMESTAMP,
    Precio_Base DECIMAL(10,2)
);

INSERT INTO Funciones (ID_Obra, ID_Sala, Fecha_Hora, Precio_Base) VALUES 
(1, 1, '2026-05-15 20:00:00', 15000.00),
(2, 2, '2026-05-16 19:30:00', 10000.00),
(3, 1, '2026-05-20 21:00:00', 25000.00);

CREATE TABLE Turnos_Funcion (
    ID SERIAL PRIMARY KEY,
    ID_Funcion INT REFERENCES Funciones(ID),
    ID_Personal_Cargo INT 
);

INSERT INTO Turnos_Funcion (ID_Funcion, ID_Personal_Cargo) VALUES 
(1, 101),
(2, 105),
(3, 102);