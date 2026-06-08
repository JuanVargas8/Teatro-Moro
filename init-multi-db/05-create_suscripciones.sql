-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
\c suscripciones

-- ============================================================
-- 1. ELIMINACIÓN DE TABLAS (ORDEN DE DEPENDENCIA)
-- ============================================================
DROP TABLE IF EXISTS Abonado;
DROP TABLE IF EXISTS Plan;

-- ============================================================
-- 2. TABLA: Plan
-- ============================================================
CREATE TABLE Plan (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    precio DECIMAL(10,2),
    beneficios TEXT
);

INSERT INTO Plan (nombre, precio, beneficios) VALUES 
('Plan inicial',0,'10% de descuento en confitería'),
('Plan Platino', 100000.00, 'Acceso gratuito anual y 20% dcto en bar'),
('Plan Temporada', 45000.00, 'Entrada gratuita a todas las obras del mes'),
('Abono Joven', 20000.00, '50% dcto en funciones de lunes a jueves');

-- ============================================================
-- 3. TABLA: Abonado
-- ============================================================
CREATE TABLE Abonado (
    id SERIAL PRIMARY KEY,
    usuario_id BIGINT, -- Relación lógica con microservicio de usuarios
    plan_id INTEGER,
    fecha_inicio DATE,
    fecha_fin DATE,
    CONSTRAINT fk_plan_abonado FOREIGN KEY (plan_id) REFERENCES Plan(id)
);

INSERT INTO Abonado (usuario_id, plan_id, fecha_inicio, fecha_fin) VALUES 
(1, 1, '2026-01-01', '2026-12-31'),
(2, 2, '2026-05-01', '2026-05-31'),
(3, 3, '2026-01-15', '2026-07-15');
