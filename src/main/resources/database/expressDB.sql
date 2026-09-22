-- ============================================
-- EXPRESS JANDTOCODE - PORTAL DE TRANSPORTE
-- Base de Datos: expressDB
-- ============================================

-- Eliminar base de datos si existe
DROP DATABASE IF EXISTS "expressDB";

-- Crear base de datos
CREATE DATABASE "expressDB"
    WITH
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8';

-- Conectarse a la base de datos (en psql: \c expressDB)

-- ============================================
-- TABLA: USER_EXPRESS (Usuario)
-- ============================================

DROP TABLE "user_express";

CREATE TABLE "user_express" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    identification VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    failed_attempts INT DEFAULT 0,
    is_blocked BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================
-- TABLA: BALANCE_INFO (Información de Saldo)
-- ============================================
CREATE TABLE "balance_info" (
    id SERIAL PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    current_balance DECIMAL(10, 2) DEFAULT 0.00,
    accumulated_recharges INT DEFAULT 0,
    last_recharge DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES "user_express"(id) ON DELETE CASCADE
);

-- ============================================
-- ÍNDICES PARA OPTIMIZACIÓN
-- ============================================
CREATE INDEX idx_user_express_identification ON "user_express"(identification);
CREATE INDEX idx_user_express_is_blocked ON "user_express"(is_blocked);
CREATE INDEX idx_balance_info_user_id ON "balance_info"(user_id);

-- ============================================
-- INSERTAR 3 USUARIOS DE PRUEBA
-- ============================================
INSERT INTO "user_express" (name, last_name, identification, password, failed_attempts, is_blocked)
VALUES
    ('Juan', 'Pérez', '123456789', 'password123', 0, false),
    ('María', 'García', '987654321', 'password456', 0, false),
    ('Carlos', 'López', '555666777', 'password789', 2, false),
    ('Pedro', 'Martínez', '111222333', 'password000', 3, true);

-- Eliminar todos los registros de user_express
DELETE FROM "user_express";

-- Resetear el ID (sequence)
ALTER SEQUENCE user_express_id_seq RESTART WITH 1;
