-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c gestion

-- ============================================================
-- 1. ELIMINACIÓN DE TABLAS (ORDEN DE DEPENDENCIA)
-- ============================================================
DROP TABLE IF EXISTS Mantenimiento_Salas;
DROP TABLE IF EXISTS Zonas_Sala;
DROP TABLE IF EXISTS Salas;

-- ============================================================
-- 2. TABLA: SALAS
-- ============================================================
CREATE TABLE Salas (
    id SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Capacidad_Total INTEGER,
    Descripcion_Tecnica TEXT
);

INSERT INTO Salas (Nombre, Capacidad_Total, Descripcion_Tecnica) VALUES 
('Gran Teatro Central', 500, 'Sistema de sonido Dolby Atmos y proyector 4K'),
('Sala Alternativa B', 120, 'Escenario circular y acústica para teatro íntimo'),
('Pequeño Auditorio', 50, 'Ideal para monólogos y recitales acústicos');

-- ============================================================
-- 3. TABLA: ZONAS_SALA
-- ============================================================
CREATE TABLE Zonas_Sala (
    id SERIAL PRIMARY KEY,
    Nombre VARCHAR(100),
    Multiplicador_Precio DECIMAL(5,2),
    ID_Sala INTEGER,
    CONSTRAINT fk_sala_zona FOREIGN KEY (ID_Sala) REFERENCES Salas(id)
);

INSERT INTO Zonas_Sala (Nombre, Multiplicador_Precio, ID_Sala) VALUES 
('Platea VIP', 1.50, 1),
('Platea General', 1.00, 1),
('Balcón Superior', 0.80, 1),
('Zona Única', 1.00, 2);

-- ============================================================
-- 4. TABLA: MANTENIMIENTO_SALAS
-- ============================================================
CREATE TABLE Mantenimiento_Salas (
    id SERIAL PRIMARY KEY,
    Fecha_Inicio DATE,
    Fecha_Fin DATE,
    Descripcion TEXT,
    ID_Sala INTEGER,
    CONSTRAINT fk_sala_mantenimiento FOREIGN KEY (ID_Sala) REFERENCES Salas(id)
);

INSERT INTO Mantenimiento_Salas (Fecha_Inicio, Fecha_Fin, Descripcion, ID_Sala) VALUES 
('2024-07-01', '2024-07-05', 'Barnizado de escenario y cambio de luminarias', 1),
('2024-08-15', '2024-08-16', 'Revisión técnica de sonido', 2),
('2024-09-10', '2024-09-12', 'Limpieza profunda de alfombras y tapicería', 3);