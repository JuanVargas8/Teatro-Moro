-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
<<<<<<< HEAD
\c notificaciones
=======
--\c notificaciones
>>>>>>> f2a52777114bd406ec5049a084b553c4601114be

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Plantillas_Notificacion;
-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Plantillas_Notificacion (
    ID SERIAL PRIMARY KEY,
    Tipo VARCHAR(50),
    Cuerpo_Texto TEXT
);

INSERT INTO Plantillas_Notificacion (Tipo, Cuerpo_Texto) VALUES 
('Confirmacion', 'Hola {nombre}, tu ticket para {obra} ha sido emitido.'),
('Recordatorio', 'No olvides tu función de mañana a las {hora}.'),
('Cancelacion', 'Lamentamos informar que la función de {obra} ha sido cancelada.');