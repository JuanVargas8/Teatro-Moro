-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
\c pagos

-- ============================================================
-- 1. ELIMINACIÓN DE TABLAS (ORDEN DE DEPENDENCIA)
-- ============================================================
DROP TABLE IF EXISTS reembolsos;
DROP TABLE IF EXISTS transacciones;
DROP TABLE IF EXISTS metodos_pago_usuario;

-- ============================================================
-- 2. TABLA: METODOS_PAGO_USUARIO
-- ============================================================
CREATE TABLE metodos_pago_usuario (
    id SERIAL PRIMARY KEY,
    id_usuario BIGINT,
    token_pasarela VARCHAR(255),
    ultimos4_digitos VARCHAR(4)
);

INSERT INTO metodos_pago_usuario (id_usuario, token_pasarela, ultimos4_digitos) VALUES 
(1, 'tok_live_a1b2c3d4e5f6', '4242'),
(2, 'tok_live_z9y8x7w6v5u4', '1234'),
(3, 'tok_live_k1j2h3g4f5e6', '8888');

-- ============================================================
-- 3. TABLA: TRANSACCIONES
-- ============================================================
CREATE TABLE transacciones (
    id SERIAL PRIMARY KEY,
    id_pedido BIGINT,
    monto DECIMAL(15,2),
    metodo_pago VARCHAR(50),
    estado VARCHAR(50)
);

INSERT INTO transacciones (id_pedido, monto, metodo_pago, estado) VALUES 
(1001, 150000.00, 'CREDITO', 'COMPLETADA'),
(1002, 45000.00, 'DEBITO', 'PENDIENTE'),
(1003, 20000.00, 'CREDITO', 'REEMBOLSADA');

-- ============================================================
-- 4. TABLA: REEMBOLSOS
-- ============================================================
CREATE TABLE reembolsos (
    id SERIAL PRIMARY KEY,
    id_transaccion BIGINT,
    motivo VARCHAR(255),
    fecha TIMESTAMP,
    monto_devuelto DECIMAL(15,2),
    CONSTRAINT fk_transaccion FOREIGN KEY (id_transaccion) REFERENCES transacciones(id)
);

INSERT INTO reembolsos (id_transaccion, motivo, fecha, monto_devuelto) VALUES 
(3, 'Cancelación de función por clima', '2024-05-11 14:30:00', 20000.00),
(1, 'Error en duplicidad de cobro', '2024-05-12 09:15:00', 150000.00);
