-- CADA BASE DE DATOS DE CADA MICROSERVICIO DEBE TENER SU PROPIO
-- SCRIPT DE CREACIÓN DE TABLAS E INSERCIÓN DE DATOS

-- Conectarse a la base de datos específica para este microservicio
\c catalogo

-- ============================================================
-- 1. ELIMINACIÓN DE TABLAS (ORDEN DE DEPENDENCIA)
-- ============================================================
DROP TABLE IF EXISTS multimedia_obra;
DROP TABLE IF EXISTS obras;
DROP TABLE IF EXISTS categorias;

-- ============================================================
-- 2. TABLA: CATEGORIAS
-- ============================================================
CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion TEXT
);

INSERT INTO categorias (nombre, descripcion) VALUES 
('Drama', 'Obras de carácter serio y conflictos emocionales profundos'),
('Comedia', 'Piezas diseñadas para el entretenimiento y la risa'),
('Musical', 'Espectáculos que combinan música, canciones y baile');

-- ============================================================
-- 3. TABLA: OBRAS
-- ============================================================
CREATE TABLE obras (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255),
    sinopsis TEXT,
    duracion INTEGER,
    clasificacion_edad VARCHAR(50),
    id_categoria INTEGER,
    CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categorias(id)
);

INSERT INTO obras (titulo, sinopsis, duracion, clasificacion_edad, id_categoria) VALUES 
('Hamlet', 'La tragedia del príncipe de Dinamarca.', 180, 'Mayores de 14', 1),
('Toc Toc', 'Seis personajes con trastornos obsesivo-compulsivos.', 90, 'Todo Público', 2),
('Los Miserables', 'Basada en la novela de Victor Hugo.', 160, 'Mayores de 12', 3);

-- ============================================================
-- 4. TABLA: MULTIMEDIA_OBRA
-- ============================================================
CREATE TABLE multimedia_obra (
    id SERIAL PRIMARY KEY,
    url_imagen VARCHAR(255),
    tipo VARCHAR(50),
    id_obra INTEGER,
    CONSTRAINT fk_obra FOREIGN KEY (id_obra) REFERENCES obras(id)
);

INSERT INTO multimedia_obra (url_imagen, tipo, id_obra) VALUES 
('https://link.com/hamlet_poster.jpg', 'Poster', 1),
('https://link.com/toctoc_escena1.png', 'Promocional', 2),
('https://link.com/miserables_backstage.jpg', 'Backstage', 3);
