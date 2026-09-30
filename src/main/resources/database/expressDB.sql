-- ============================================
-- EXPRESS JANDTOCODE - PORTAL DE TRANSPORTE
-- Base de Datos: expressDB
-- ============================================

-- Eliminar base de datos si existe
-- DROP DATABASE IF EXISTS "expressDB";

-- Crear base de datos
--CREATE DATABASE "expressDB"
--    WITH
--    ENCODING = 'UTF8'
--    LC_COLLATE = 'en_US.UTF-8'
--    LC_CTYPE = 'en_US.UTF-8';

-- Conectarse a la base de datos (en psql: \c expressDB)


-- Eliminar tablas si existen
DROP TABLE IF EXISTS "dashboard_info" CASCADE;
DROP TABLE IF EXISTS "user_express" CASCADE;

-- ============================================
-- TABLA: USER_EXPRESS (Usuario)
-- ============================================
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
-- TABLA: DASHBOARD_INFO (Informacion de Dashboard)
-- ============================================
CREATE TABLE "dashboard_info" (
    id SERIAL PRIMARY KEY,
    user_id INT UNIQUE NOT NULL,
    current_balance DECIMAL(10, 2) DEFAULT 0.00,
    accumulated_recharges INT DEFAULT 0,
    last_recharge DATE DEFAULT '1900-01-01',
    type_payment VARCHAR(100),
    entity_payment VARCHAR(100),
    value_recharge DECIMAL(10, 2) DEFAULT 0.00,
    apply_bonus BOOLEAN DEFAULT false,
    value_bonus DECIMAL(10, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES "user_express"(id) ON DELETE CASCADE
);

-- ============================================
-- INDICES PARA OPTIMIZACION
-- ============================================
CREATE INDEX idx_user_express_identification ON "user_express"(identification);
CREATE INDEX idx_user_express_is_blocked ON "user_express"(is_blocked);
CREATE INDEX idx_dashboard_info_user_id ON "dashboard_info"(user_id);

-- ============================================
-- USUARIOS DE PRUEBA (clave de todos: pass123)
-- El id se asigna en orden: 1001 = id 1, 1002 = id 2, etc.
-- ============================================
INSERT INTO "user_express" (name, last_name, identification, password, failed_attempts, is_blocked) VALUES
('Juan',   'Perez',     '1001', 'pass123', 0, false),  -- usuario nuevo
('Maria',  'Garcia',    '1002', 'pass123', 0, false),  -- usuario nuevo
('Carlos', 'Lopez',     '1003', 'pass123', 0, false),  -- 5 recargas, sin bono
('Ana',    'Martinez',  '1004', 'pass123', 0, false),  -- 10 recargas, la siguiente tiene bono
('Luis',   'Rodriguez', '1005', 'pass123', 0, false),  -- 9 recargas, una mas y llega a 10
('Sofia',  'Ramirez',   '1006', 'pass123', 3, true),   -- bloqueado
('Pedro',  'Gomez',     '1007', 'pass123', 2, false);  -- un error mas y se bloquea

-- ============================================
-- DASHBOARD DE CADA USUARIO (user_id = id de arriba)
-- Tipos de pago: Efectivo, Tarjeta
-- Bancos: PiggyBank Pop, Banco Monedita, PixelFinance, CofreFeliz Bank, CashCoon Bank
-- ============================================
INSERT INTO "dashboard_info" (user_id, current_balance, accumulated_recharges, last_recharge, type_payment, entity_payment, value_recharge, apply_bonus, value_bonus) VALUES
(1, 0.00,     0,  '1900-01-01', NULL,       NULL,              0.00,    false, 0.00),
(2, 0.00,     0,  '1900-01-01', NULL,       NULL,              0.00,    false, 0.00),
(3, 3450.00,  5,  '2026-09-15', 'Efectivo', 'PiggyBank Pop',   800.00,  false, 0.00),
(4, 15000.50, 10, '2026-09-20', 'Tarjeta',  'Banco Monedita',  5000.00, false, 0.00),
(5, 8999.25,  9,  '2026-09-18', 'Tarjeta',  'PixelFinance',    1500.00, false, 0.00),
(6, 1200.00,  2,  '2026-09-10', 'Efectivo', 'CofreFeliz Bank', 600.00,  false, 0.00),
(7, 450.00,   1,  '2026-09-12', 'Tarjeta',  'CashCoon Bank',   450.00,  false, 0.00);


-- Eliminar todos los registros de user_express
--DELETE FROM "user_express";
--DELETE FROM "balance_info";

-- Resetear el ID (sequence)
--ALTER SEQUENCE user_express_id_seq RESTART WITH 1;
--ALTER SEQUENCE balance_info_id_seq RESTART WITH 1;
