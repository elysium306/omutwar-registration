-- ============================
-- V2: INDEXES & CONSTRAINTS
-- Idempotent, safe to re-run
-- ============================


-- USERS
ALTER TABLE users
    ADD CONSTRAINT users_status_check
    CHECK (status IN ('ACTIVE', 'DISABLED', 'PENDING'))
    NOT VALID;

CREATE INDEX IF NOT EXISTS idx_users_email_lower
    ON users (LOWER(email));

CREATE INDEX IF NOT EXISTS idx_users_metadata_gin
    ON users USING GIN (metadata);


-- ROLES
ALTER TABLE roles
    ADD CONSTRAINT roles_name_check
    CHECK (name ~ '^[A-Z_]+$')
    NOT VALID;

CREATE INDEX IF NOT EXISTS idx_roles_created_at
    ON roles (created_at);


-- USER ROLES
CREATE INDEX IF NOT EXISTS idx_user_roles_role_id
    ON user_roles (role_id);

-- Composite index for fast permission lookups
CREATE INDEX IF NOT EXISTS idx_user_roles_user_role
    ON user_roles (user_id, role_id);


-- PRODUCTS
ALTER TABLE products
    ADD CONSTRAINT products_price_check
    CHECK (price >= 0)
    NOT VALID;

CREATE INDEX IF NOT EXISTS idx_products_price
    ON products (price);

CREATE INDEX IF NOT EXISTS idx_products_metadata_gin
    ON products USING GIN (metadata);


-- ORDERS
ALTER TABLE orders
    ADD CONSTRAINT orders_status_check
    CHECK (status IN ('PENDING', 'PAID', 'CANCELLED', 'REFUNDED'))
    NOT VALID;

CREATE INDEX IF NOT EXISTS idx_orders_status
    ON orders (status);

CREATE INDEX IF NOT EXISTS idx_orders_user_created
    ON orders (user_id, created_at DESC);

CREATE INDEX IF NOT EXISTS idx_orders_metadata_gin
    ON orders USING GIN (metadata);


-- LOGIN AUDIT
CREATE INDEX IF NOT EXISTS idx_login_audit_timestamp
    ON login_audit (login_timestamp DESC);

CREATE INDEX IF NOT EXISTS idx_login_audit_success
    ON login_audit (success_flag);


-- SESSIONS
ALTER TABLE sessions
    ADD CONSTRAINT sessions_token_length_check
    CHECK (char_length(token) >= 32)
    NOT VALID;

CREATE INDEX IF NOT EXISTS idx_sessions_expires_at
    ON sessions (expires_at);

CREATE INDEX IF NOT EXISTS idx_sessions_metadata_gin
    ON sessions USING GIN (metadata);


-- PASSWORD RESET TOKENS
ALTER TABLE password_reset_tokens
    ADD CONSTRAINT password_reset_tokens_token_length_check
    CHECK (char_length(token) >= 32)
    NOT VALID;

CREATE INDEX IF NOT EXISTS idx_password_reset_expires
    ON password_reset_tokens (expires_at);


-- EMAIL VERIFICATION
ALTER TABLE email_verification
    ADD CONSTRAINT email_verification_token_length_check
    CHECK (char_length(token) >= 32)
    NOT VALID;

CREATE INDEX IF NOT EXISTS idx_email_verification_created
    ON email_verification (created_at);