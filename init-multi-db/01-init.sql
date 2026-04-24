-- ES FUNDAMENTAL EJECUTAR ESTE SCRIPT QUE PERMITE ELIMINAR LAS BASES DE DATOS
-- SI ES QUE EXISTEN, PARA LUEGO CREARLAS LIMPIAS SIN TABLAS Y DESDE CERO

SELECT 'CREATE DATABASE ticketing'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'ticketing') \gexec

SELECT 'CREATE DATABASE reserva'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'reserva') \gexec

SELECT 'CREATE DATABASE pagos'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'pagos') \gexec

SELECT 'CREATE DATABASE suscripciones'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'suscripciones') \gexec

SELECT 'CREATE DATABASE catalogo'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'catalogo') \gexec

SELECT 'CREATE DATABASE funciones'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'funciones') \gexec

SELECT 'CREATE DATABASE gestion'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'gestion') \gexec

SELECT 'CREATE DATABASE usuarios'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'usuarios') \gexec

SELECT 'CREATE DATABASE feedback'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'feedback') \gexec

SELECT 'CREATE DATABASE cafeteria'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'cafeteria') \gexec

SELECT 'CREATE DATABASE merchandising'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'merchandising') \gexec

SELECT 'CREATE DATABASE personal'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'personal') \gexec

SELECT 'CREATE DATABASE promociones'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'promociones') \gexec

SELECT 'CREATE DATABASE informes'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'informes') \gexec

SELECT 'CREATE DATABASE notificaciones'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'notificaciones') \gexec