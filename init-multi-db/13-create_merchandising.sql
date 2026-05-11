-- ============================================================
-- BASE DE DATOS: promociones
-- Cada microservicio tiene su propio script
-- ============================================================

-- Conectarse a la base de datos específica
-- \c promociones

-- ============================================================
-- 1. ELIMINACIÓN DE TABLAS
-- ============================================================
DROP TABLE IF EXISTS campana;
DROP TABLE IF EXISTS programa_lealtad;
DROP TABLE IF EXISTS promocion;

-- ============================================================
-- 2. TABLA: CAMPANA
-- ============================================================
CREATE TABLE campana (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    fecha_inicio DATE,
    fecha_fin DATE
);

INSERT INTO campana (nombre, fecha_inicio, fecha_fin) VALUES 
('Temporada de Invierno', '2026-06-01', '2026-08-31'),
('Campaña Estudiantes', '2026-03-01', '2026-12-15'),
('Black Friday Teatro', '2026-11-20', '2026-11-30');

-- ============================================================
-- 3. TABLA: PROGRAMA_LEALTAD
-- ============================================================
CREATE TABLE programa_lealtad (
    id SERIAL PRIMARY KEY,
    nombre_nivel VARCHAR(100),
    puntos_minimos INTEGER,
    beneficio TEXT
);

INSERT INTO programa_lealtad (nombre_nivel, puntos_minimos, beneficio) VALUES 
('Bronce', 0, 'Sorteos mensuales de entradas'),
('Plata', 500, '10% de descuento permanente en cafetería'),
('Oro', 1500, 'Acceso preferencial y meet & greet con actores');

-- ============================================================
-- 4. TABLA: PROMOCION
-- ============================================================
CREATE TABLE promocion (
    id SERIAL PRIMARY KEY,
    codigo VARCHAR(50),
    porcentaje DECIMAL(5,2),
    descripcion VARCHAR(255)
);

INSERT INTO promocion (codigo, porcentaje, descripcion) VALUES 
('TEATRO20', 20.00, 'Descuento por primera compra'),
('ESTUDIANTE50', 50.00, 'Válido solo con credencial vigente'),
('PROMO_LUNES', 15.00, 'Descuento especial funciones de lunes');