-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
\c informes

-- ============================================================
-- 1. ELIMINACIÓN DE TABLAS
-- ============================
DROP TABLE IF EXISTS metricas_ocupacion;
DROP TABLE IF EXISTS snapshot_ventas;
DROP TABLE IF EXISTS tendencias_busqueda;

-- ============================================================
-- 2. TABLA: METRICAS_OCUPACION
-- ============================================================
CREATE TABLE metricas_ocupacion (
    id SERIAL PRIMARY KEY,
    ID_Funcion BIGINT, -- Relación lógica con el microservicio de funciones
    Porcentaje_Llenado DECIMAL(5,2)
);

INSERT INTO metricas_ocupacion (ID_Funcion, Porcentaje_Llenado) VALUES 
(1, 85.50),
(2, 42.00),
(3, 100.00);

-- ============================================================
-- 3. TABLA: SNAPSHOT_VENTAS
-- ============================================================
CREATE TABLE snapshot_ventas (
    id SERIAL PRIMARY KEY,
    Fecha DATE,
    Total_Recaudado DECIMAL(15,2),
    Total_Tickets INTEGER
);

INSERT INTO snapshot_ventas (Fecha, Total_Recaudado, Total_Tickets) VALUES 
('2024-05-10', 1250000.00, 150),
('2024-05-11', 2100500.50, 245),
('2024-05-12', 980000.00, 88);

-- ============================================================
-- 4. TABLA: TENDENCIAS_BUSQUEDA
-- ============================================================
CREATE TABLE tendencias_busqueda (
    id SERIAL PRIMARY KEY,
    Termino_Busqueda VARCHAR(255),
    Cantidad_Resultados INTEGER,
    Fecha DATE
);

INSERT INTO tendencias_busqueda (Termino_Busqueda, Cantidad_Resultados, Fecha) VALUES 
('Teatro Musical', 45, '2024-05-11'),
('Obras de Drama', 12, '2024-05-11'),
('Hamlet', 89, '2024-05-12');
