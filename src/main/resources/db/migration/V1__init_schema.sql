-- =========================
-- USERS
-- =========================
CREATE TABLE users (
    id BINARY(16) PRIMARY KEY,
    email VARCHAR(150) NOT NULL UNIQUE,
    username VARCHAR(100) UNIQUE,
    password VARCHAR(255) NOT NULL,

    enabled BOOLEAN DEFAULT TRUE,
    account_non_locked BOOLEAN DEFAULT TRUE,
    account_non_expired BOOLEAN DEFAULT TRUE,
    credentials_non_expired BOOLEAN DEFAULT TRUE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- =========================
-- ROLES
-- =========================
CREATE TABLE roles (
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
);

-- =========================
-- USER_ROLES
-- =========================
CREATE TABLE user_roles (
    id BINARY(16) PRIMARY KEY,

    user_id BINARY(16) NOT NULL,
    role_id BINARY(16) NOT NULL,

    CONSTRAINT uk_user_role UNIQUE (user_id, role_id),

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_roles_role
        FOREIGN KEY (role_id) REFERENCES roles(id)
        ON DELETE CASCADE
);

-- =========================
-- REFRESH TOKENS
-- =========================
CREATE TABLE refresh_tokens (
    id BINARY(16) PRIMARY KEY,
    token VARCHAR(500) NOT NULL UNIQUE,

    user_id BINARY(16) NOT NULL,

    revoked BOOLEAN DEFAULT FALSE,
    expired BOOLEAN DEFAULT FALSE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_refresh_tokens_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);

