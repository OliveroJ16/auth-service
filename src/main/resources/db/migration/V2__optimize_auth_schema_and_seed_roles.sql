-- ===========================
-- DATA PRE-LOADING
-- ===========================
INSERT INTO roles (id, name, description) VALUES
(UUID_TO_BIN(UUID()), 'ROLE_USER', 'Default role for all registered users'),
(UUID_TO_BIN(UUID()), 'ROLE_ADMIN', 'System administrator with full access');