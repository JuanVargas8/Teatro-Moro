-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c notificaciones

-- ============================================================
-- 1. ELIMINACIÓN DE TABLAS (ORDEN DE DEPENDENCIA)
-- ============================================================
DROP TABLE IF EXISTS Cola_Envios;
DROP TABLE IF EXISTS Plantillas_Notificacion;
DROP TABLE IF EXISTS Preferencias_Notificacion;

-- ============================================================
-- 2. TABLA: PREFERENCIAS_NOTIFICACION
-- ============================================================
CREATE TABLE Preferencias_Notificacion (
    id_usuario INTEGER PRIMARY KEY,
    email BOOLEAN NOT NULL,
    sms BOOLEAN NOT NULL
);

INSERT INTO Preferencias_Notificacion (id_usuario, email, sms) VALUES 
(1, TRUE, FALSE),
(2, TRUE, TRUE),
(3, FALSE, FALSE);

-- ============================================================
-- 3. TABLA: PLANTILLAS_NOTIFICACION
-- ============================================================
CREATE TABLE Plantillas_Notificacion (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    cuerpo TEXT NOT NULL
);

INSERT INTO Plantillas_Notificacion (tipo, cuerpo) VALUES 
('BIENVENIDA', 'Hola {nombre}, ¡bienvenido a nuestra plataforma de teatro!'),
('CONFIRMACION_COMPRA', 'Tu entrada para {obra} ha sido confirmada. ID: {ticket}'),
('RECORDATORIO_FUNCION', 'Te recordamos que tu función comienza en 2 horas.');

-- ============================================================
-- 4. TABLA: COLA_ENVIOS
-- ============================================================
CREATE TABLE Cola_Envios (
    id SERIAL PRIMARY KEY,
    id_usuario INTEGER NOT NULL,
    estado VARCHAR(20) NOT NULL,
    reintentos INTEGER NOT NULL,
    id_plantilla INTEGER NOT NULL,
    CONSTRAINT fk_plantilla FOREIGN KEY (id_plantilla) REFERENCES Plantillas_Notificacion(id)
);

INSERT INTO Cola_Envios (id_usuario, estado, reintentos, id_plantilla) VALUES 
(1, 'PENDIENTE', 0, 1),
(2, 'ENVIADO', 1, 2),
(1, 'FALLIDO', 3, 3);
