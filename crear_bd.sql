-- =====================================================
-- Script de base de datos del proyecto ProyectoJavaFX
-- =====================================================

CREATE DATABASE IF NOT EXISTS bd_proyectojavafx
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE bd_proyectojavafx;

CREATE TABLE IF NOT EXISTS usuariossistema (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    Nombre        VARCHAR(255),
    Apellido      VARCHAR(255),
    Usuario       VARCHAR(255) UNIQUE,
    Clave         VARCHAR(255) NOT NULL,
    Correo        VARCHAR(255),
    Roll          ENUM('ADMIN','USUARIO') NOT NULL DEFAULT 'USUARIO',
    Estado        ENUM('ACTIVO','INACTIVO') NOT NULL DEFAULT 'ACTIVO',
    FechaCreacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FechaEdicion  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                  ON UPDATE CURRENT_TIMESTAMP
);

-- Usuario de prueba. CAMBIAR 'admin123' por la clave que quieras.
-- MD5() calcula el hash: nunca escribir el hash a mano.
INSERT INTO usuariossistema (Nombre, Apellido, Usuario, Clave, Correo, Roll, Estado)
VALUES ('Admin', 'Sistema', 'admin', MD5('admin123'), 'admin@correo.com', 'ADMIN', 'ACTIVO');