-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
--\c notificaciones

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Cola_Envios;
DROP TABLE IF EXISTS Preferencias_Notificacion;
DROP TABLE IF EXISTS Plantillas_Notificacion;

CREATE TABLE Plantillas_Notificacion (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50),
    cuerpo TEXT
);

CREATE TABLE Preferencias_Notificacion (
    id_usuario INT PRIMARY KEY,
    email BOOLEAN,
    sms BOOLEAN
);

CREATE TABLE Cola_Envios (
    id SERIAL PRIMARY KEY,
    id_usuario INT,
    id_plantilla INT,
    estado VARCHAR(20),
    reintentos INT,
    FOREIGN KEY (id_plantilla) REFERENCES Plantillas_Notificacion(id)
);

INSERT INTO Plantillas_Notificacion VALUES
(DEFAULT,'Confirmacion','Compra realizada'),
(DEFAULT,'Recordatorio','Evento próximo');

INSERT INTO Preferencias_Notificacion VALUES
(101,TRUE,FALSE),
(102,TRUE,TRUE);

INSERT INTO Cola_Envios VALUES
(DEFAULT,101,1,'Pendiente',0),
(DEFAULT,102,2,'Enviado',1);