-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c funciones

-- ============================================================
-- 1. ELIMINACIÓN DE TABLAS (ORDEN DE DEPENDENCIA)
-- ============================================================
DROP TABLE IF EXISTS turnos_funcion;
DROP TABLE IF EXISTS funciones;
DROP TABLE IF EXISTS excepciones_horario;

-- ============================================================
-- 2. TABLA: EXCEPCIONES_HORARIO
-- ============================================================
CREATE TABLE excepciones_horario (
    id SERIAL PRIMARY KEY,
    fecha DATE,
    motivo VARCHAR(255)
);

INSERT INTO excepciones_horario (fecha, motivo) VALUES 
('2024-12-25', 'Feriado de Navidad'),
('2024-05-01', 'Día del Trabajador'),
('2024-06-15', 'Mantenimiento preventivo de sala');

-- ============================================================
-- 3. TABLA: FUNCIONES
-- ============================================================
CREATE TABLE funciones (
    id SERIAL PRIMARY KEY,
    id_obra BIGINT, -- Relación lógica (ID de otro microservicio o tabla)
    id_sala BIGINT, -- Relación lógica
    fecha_hora TIMESTAMP,
    precio_base DECIMAL(10,2)
);

INSERT INTO funciones (id_obra, id_sala, fecha_hora, precio_base) VALUES 
(1, 101, '2024-06-20 20:00:00', 15000.00),
(2, 102, '2024-06-20 21:30:00', 12000.00),
(3, 101, '2024-06-21 19:00:00', 25000.00);

-- ============================================================
-- 4. TABLA: TURNOS_FUNCION
-- ============================================================
CREATE TABLE turnos_funcion (
    id SERIAL PRIMARY KEY,
    id_personal_cargo BIGINT, -- Relación lógica (ID de otro MS)
    id_funcion INTEGER,
    CONSTRAINT fk_funcion FOREIGN KEY (id_funcion) REFERENCES funciones(id)
);

INSERT INTO turnos_funcion (id_personal_cargo, id_funcion) VALUES 
(50, 1),
(51, 2),
(52, 3);