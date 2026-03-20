-- ============================================
-- V3: Idempotency Keys + Advanced Constraints
-- Safe, idempotent, enterprise-grade
-- ============================================


-- ============================
-- 1. Add idempotency keys
-- ============================

-- USERS: prevent duplicate registration attempts
ALTER TABLE users
    ADD COLUMN IF NOT EXISTS idempotency_key TEXT UNIQUE;

CREATE INDEX IF NOT EXISTS idx_users_idempotency_key
    ON users (idempotency_key);


-- ORDERS: prevent duplicate order creation
ALTER TABLE orders
    ADD COLUMN IF NOT EXISTS idempotency_key TEXT UNIQUE;

CREATE INDEX IF NOT EXISTS idx_orders_idempotency_key
    ON orders (idempotency_key);


-- SESSIONS: prevent duplicate session creation
ALTER TABLE sessions
    ADD COLUMN IF NOT EXISTS idempotency_key TEXT UNIQUE;

CREATE INDEX IF NOT EXISTS idx_sessions_idempotency_key
    ON sessions (idempotency_key);


-- ============================
-- 2. Add request fingerprints
-- ============================

-- Helps detect replay attacks, fraud, and duplicate submissions
ALTER TABLE login_audit
    ADD COLUMN IF NOT EXISTS request_fingerprint TEXT;

CREATE INDEX IF NOT EXISTS idx_login_audit_fingerprint
    ON login_audit (request_fingerprint);


-- ============================
-- 3. Add advanced constraints
-- ============================

-- USERS: enforce email format
ALTER TABLE users
    ADD CONSTRAINT users_email_format_check
    CHECK (email ~* '^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$')
    NOT VALID;

-- ORDERS: enforce valid monetary values
ALTER TABLE orders
    ADD CONSTRAINT orders_total_amount_check
    CHECK (total_amount >= 0)
    NOT VALID;

-- SESSIONS: enforce token length
ALTER TABLE sessions
    ADD CONSTRAINT sessions_token_min_length_check
    CHECK (char_length(token) >= 32)
    NOT VALID;


-- ============================
-- 4. ENUM-like constraints
-- ============================

ALTER TABLE users
    ADD CONSTRAINT users_status_enum
    CHECK (status IN ('ACTIVE', 'DISABLED', 'PENDING'))
    NOT VALID;

ALTER TABLE orders
    ADD CONSTRAINT orders_status_enum
    CHECK (status IN ('PENDING', 'PAID', 'CANCELLED', 'REFUNDED'))
    NOT VALID;


-- ============================
-- 5. Add updated_at triggers
-- ============================

-- Create function if not exists
CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- USERS
DROP TRIGGER IF EXISTS trg_users_updated_at ON users;
CREATE TRIGGER trg_users_updated_at
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

-- PRODUCTS
ALTER TABLE products
    ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP DEFAULT NOW();

DROP TRIGGER IF EXISTS trg_products_updated_at ON products;
CREATE TRIGGER trg_products_updated_at
BEFORE UPDATE ON products
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();

-- ORDERS
ALTER TABLE orders
    ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP DEFAULT NOW();

DROP TRIGGER IF EXISTS trg_orders_updated_at ON orders;
CREATE TRIGGER trg_orders_updated_at
BEFORE UPDATE ON orders
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();


-- ============================
-- 6. Add soft-delete support
-- ============================

ALTER TABLE users
    ADD COLUMN IF NOT EXISTS deleted_at TIMESTAMP;

ALTER TABLE products
    ADD COLUMN IF NOT EXISTS deleted_at TIMESTAMP;

ALTER TABLE orders
    ADD COLUMN IF NOT EXISTS deleted_at TIMESTAMP;

CREATE INDEX IF NOT EXISTS idx_users_deleted_at ON users(deleted_at);
CREATE INDEX IF NOT EXISTS idx_products_deleted_at ON products(deleted_at);
CREATE INDEX IF NOT EXISTS idx_orders_deleted_at ON orders(deleted_at);