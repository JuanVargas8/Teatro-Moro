-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
\c personal

-- ============================================================
-- 1. ELIMINACIÓN DE TABLAS (ORDEN DE DEPENDENCIA)
-- ============================================================
DROP TABLE IF EXISTS asistencia;
DROP TABLE IF EXISTS elencos_obra;
DROP TABLE IF EXISTS personal;

-- ============================================================
-- 2. TABLA: PERSONAL
-- ============================================================
CREATE TABLE personal (
    id SERIAL PRIMARY KEY,
    Nombre VARCHAR(255),
    Especialidad VARCHAR(100), -- Actor, Técnico, etc.
    Tipo_Contrato VARCHAR(100)
);

INSERT INTO personal (Nombre, Especialidad, Tipo_Contrato) VALUES 
('Ricardo Darín', 'Actor', 'Planta'),
('Elena Portillo', 'Técnico de Iluminación', 'Freelance'),
('Julián Martínez', 'Actor', 'Temporal');

-- ============================================================
-- 3. TABLA: ELENCOS_OBRA
-- ============================================================
CREATE TABLE elencos_obra (
    id SERIAL PRIMARY KEY,
    ID_Obra BIGINT, -- Relación lógica con microservicio de obras
    ID_Personal INTEGER,
    Rol_En_Obra VARCHAR(255),
    CONSTRAINT fk_personal_elenco FOREIGN KEY (ID_Personal) REFERENCES personal(id)
);

INSERT INTO elencos_obra (ID_Obra, ID_Personal, Rol_En_Obra) VALUES 
(1, 1, 'Protagonista - Hamlet'),
(1, 3, 'Laertes'),
(2, 2, 'Jefe de Luces');

-- ============================================================
-- 4. TABLA: ASISTENCIA
-- ============================================================
CREATE TABLE asistencia (
    id SERIAL PRIMARY KEY,
    ID_Personal INTEGER,
    ID_Funcion BIGINT, -- Relación lógica con microservicio de funciones
    Hora_Entrada TIMESTAMP,
    CONSTRAINT fk_personal_asistencia FOREIGN KEY (ID_Personal) REFERENCES personal(id)
);

INSERT INTO asistencia (ID_Personal, ID_Funcion, Hora_Entrada) VALUES 
(1, 101, '2026-05-11 18:30:00'),
(2, 101, '2026-05-11 17:45:00'),
(3, 102, '2026-05-11 20:15:00');
