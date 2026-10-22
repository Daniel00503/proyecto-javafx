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


-- =====================================================
-- Laboratorio 3: aulas
-- =====================================================

-- tipo_aula y estado deben coincidir letra por letra
-- con los enums TipoAula y EstadoAula de Java.
CREATE TABLE IF NOT EXISTS aulas (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(50)  NOT NULL,
    edificio     VARCHAR(50)  NOT NULL,
    planta       INT          NOT NULL,
    capacidad    INT          NOT NULL,
    tipo_aula    ENUM('NORMAL','CENTRO_DE_COMPUTO','LABORATORIO','ESPECIAL') NOT NULL,
    responsable  VARCHAR(100) NOT NULL,
    estado       ENUM('ASIGNADA','ASIGNADA_TEMPORALMENTE','EN_MANTENIMIENTO','DISPONIBLE') NOT NULL,
    CONSTRAINT uk_aulas_nombre_edificio UNIQUE (nombre, edificio)
);
