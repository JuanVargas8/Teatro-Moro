-- ============================================================
-- BASE DE DATOS: promociones
-- Cada microservicio tiene su propio script
-- ============================================================

-- Conectarse a la base de datos específica
-- \c promociones

-- ============================================================
-- 1. ELIMINACIÓN (Orden inverso de dependencias)
-- ============================================================
DROP TABLE IF EXISTS Promociones;
DROP TABLE IF EXISTS Programa_Lealtad;
DROP TABLE IF EXISTS Campana;

-- ============================================================
-- 2. TABLA: Campana
-- ============================================================
CREATE TABLE Campana (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL
);

INSERT INTO Campana (nombre, fecha_inicio, fecha_fin) VALUES
('Campaña Verano 2026', '2026-01-01', '2026-02-28'),
('Campaña Invierno 2026', '2026-06-01', '2026-07-31');

-- ============================================================
-- 3. TABLA: ProgramaLealtad
-- ============================================================
CREATE TABLE Programa_Lealtad (
    id SERIAL PRIMARY KEY,
    nombre_nivel VARCHAR(100) NOT NULL,
    puntos_minimos INT NOT NULL,
    beneficio VARCHAR(255)
);

INSERT INTO Programa_Lealtad (nombre_nivel, puntos_minimos, beneficio) VALUES
('Bronce', 0, 'Acceso básico a promociones'),
('Plata', 500, 'Descuento 10% en entradas'),
('Oro', 1000, 'Descuento 20% + prioridad en eventos');

-- ============================================================
-- 4. TABLA: Promocion
-- ============================================================
CREATE TABLE Promociones (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL,
    porcentaje DECIMAL(5,2) NOT NULL,
    descripcion VARCHAR(255)
);

INSERT INTO Promociones (codigo, porcentaje, descripcion) VALUES
('PROMO10', 10.00, 'Descuento general del 10%'),
('PROMO20', 20.00, 'Descuento especial del 20%'),
('TEATRO50', 50.00, 'Promoción especial eventos seleccionados');