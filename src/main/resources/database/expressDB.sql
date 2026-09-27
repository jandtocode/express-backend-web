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

DROP TABLE "dashboard_info";
-- ============================================
-- TABLA: DASHBOARD_INFO (Información de Dashboard)
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
-- ÍNDICES PARA OPTIMIZACIÓN
-- ============================================
CREATE INDEX idx_user_express_identification ON "user_express"(identification);
CREATE INDEX idx_user_express_is_blocked ON "user_express"(is_blocked);
CREATE INDEX idx_dashboard_info_user_id ON "dashboard_info"(user_id);

-- ============================================
-- INSERTAR 5 USUARIOS DE PRUEBA
-- ============================================
-- Insertar usuarios en user_express
INSERT INTO "user_express" (name, last_name, identification, password, failed_attempts, is_blocked) VALUES
('Juan', 'Perez', '1001', 'pass123', 0, false),
('Maria', 'Garcia', '1002', 'pass123', 0, false),
('Carlos', 'Lopez', '1003', 'pass123', 0, false),
('Ana', 'Martinez', '1004', 'pass123', 0, false),
('Luis', 'Rodriguez', '1005', 'pass123', 0, false);

-- Insertar información de dashboard en dashboard_info
-- Insertar datos en dashboard_info
INSERT INTO "dashboard_info" (user_id, current_balance, accumulated_recharges, last_recharge, type_payment, entity_payment, value_recharge, apply_bonus, value_bonus) VALUES
(1, 15000.50, 10, '2024-09-20', 'Tarjeta Credito', 'Banco Bogota', 5000.00, true, 500.00),
(2, 0.00, 0, '1900-01-01', NULL, NULL, 0.00, false, 0.00),
(3, 5234.75, 8, '2024-09-18', 'Transferencia', 'Banco BBVA', 2000.00, true, 200.00),
(4, 8999.25, 12, '2024-09-19', 'Tarjeta Debito', 'Banco Caja Social', 1500.00, false, 0.00),
(5, 3450.00, 5, '2024-09-17', 'Billetera Digital', 'Nequi', 800.00, true, 80.00);

-- Eliminar todos los registros de user_express
DELETE FROM "user_express";
DELETE FROM "balance_info";

-- Resetear el ID (sequence)
ALTER SEQUENCE user_express_id_seq RESTART WITH 1;
ALTER SEQUENCE balance_info_id_seq RESTART WITH 1;
