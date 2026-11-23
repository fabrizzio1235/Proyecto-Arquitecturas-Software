-- Crea la tabla (si no existe) y carga datos de prueba.
CREATE DATABASE IF NOT EXISTS crud_db;
USE crud_db;

CREATE TABLE IF NOT EXISTS productos (
                                         id     BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         nombre VARCHAR(100)   NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock  INT            NOT NULL
    );

INSERT INTO productos (nombre, precio, stock) VALUES
                                                  ('Teclado mecánico', 899.00, 15),
                                                  ('Mouse inalámbrico', 349.50, 40),
                                                  ('Monitor 24 pulgadas', 2899.99, 8),
                                                  ('Cable HDMI 2m', 120.00, 100),
                                                  ('Memoria USB 64GB', 180.00, 60),
                                                  ('Audífonos con micrófono', 520.00, 25),
                                                  ('Webcam HD', 640.00, 12);