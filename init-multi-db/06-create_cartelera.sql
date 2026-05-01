\c cartelera

-- ============================================================
-- 1. ELIMINACIÓN (Orden jerárquico inverso)
-- ============================================================
DROP TABLE IF EXISTS Multimedia_Obra;
DROP TABLE IF EXISTS Categorias;
DROP TABLE IF EXISTS Obras;

-- ============================================================
-- 2. PROYECCIONES MÍNIMAS LOCALES
-- ============================================================
CREATE TABLE Obras (
    ID SERIAL PRIMARY KEY,
    Titulo VARCHAR(100),
    Sinopsis TEXT,
    Duracion INT,
    Clasificacion_Edad VARCHAR(10)
);

INSERT INTO Obras (Titulo, Sinopsis, Duracion, Clasificacion_Edad) VALUES 
('Hamlet', 'La tragedia del Príncipe de Dinamarca.', 150, '+14'),
('El Tartufo', 'Comedia sobre la hipocresía y la falsa devoción.', 120, 'TE'),
('Los Miserables', 'El épico musical basado en la novela de Victor Hugo.', 180, '+12');

CREATE TABLE Categorias (
    ID SERIAL PRIMARY KEY,
    Nombre VARCHAR(50),
    Descripcion TEXT
);

INSERT INTO Categorias (Nombre, Descripcion) VALUES 
('Drama', 'Obras con tono serio y conflictos emocionales profundos.'),
('Comedia', 'Obras de tono ligero y humorístico.'),
('Musical', 'Obras donde la música y el canto son el centro de la narrativa.');

CREATE TABLE Multimedia_Obra (
    ID SERIAL PRIMARY KEY,
    ID_Obra INT REFERENCES Obras(ID),
    URL_Imagen VARCHAR(255),
    Tipo VARCHAR(50)
);

INSERT INTO Multimedia_Obra (ID_Obra, URL_Imagen, Tipo) VALUES 
(1, 'https://dominio.com/posters/hamlet.jpg', 'Poster'),
(2, 'https://dominio.com/trailers/tartufo.mp4', 'Trailer'),
(3, 'https://dominio.com/posters/miserables.jpg', 'Poster');