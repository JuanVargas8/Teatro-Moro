\c gestion

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Mantenimiento_Salas;
DROP TABLE IF EXISTS Zonas_Sala;
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

CREATE TABLE Zonas_Sala (
    ID SERIAL PRIMARY KEY,
    ID_Sala INT REFERENCES Salas(ID),
    Nombre VARCHAR(50),
    Multiplicador_Precio DECIMAL(4,2)
);

INSERT INTO Zonas_Sala (ID_Sala, Nombre, Multiplicador_Precio) VALUES 
(1, 'Platea Baja', 1.50),
(1, 'Platea Alta', 1.00),
(2, 'General', 1.00);

CREATE TABLE Mantenimiento_Salas (
    ID SERIAL PRIMARY KEY,
    ID_Sala INT REFERENCES Salas(ID),
    Fecha_Inicio DATE,
    Fecha_Fin DATE,
    Descripcion TEXT
);

INSERT INTO Mantenimiento_Salas (ID_Sala, Fecha_Inicio, Fecha_Fin, Descripcion) VALUES 
(1, '2026-06-01', '2026-06-15', 'Cambio de butacas en Platea Baja'),
(3, '2026-07-10', '2026-07-12', 'Mantenimiento del sistema de iluminación');