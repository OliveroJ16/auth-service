-- =========================
-- ROLES
-- =========================
CREATE TABLE roles (
    id BINARY(16) NOT NULL,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_roles PRIMARY KEY (id),
    CONSTRAINT uk_role_name UNIQUE (name)
) ENGINE=InnoDB;

-- =========================
-- USERS
-- =========================
CREATE TABLE users (
    id BINARY(16) NOT NULL,
    email VARCHAR(150) NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,

    -- Spring Security Flags
    enabled BOOLEAN DEFAULT TRUE,
    account_non_locked BOOLEAN DEFAULT TRUE,
    account_non_expired BOOLEAN DEFAULT TRUE,
    credentials_non_expired BOOLEAN DEFAULT TRUE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uk_user_email UNIQUE (email),
    CONSTRAINT uk_user_username UNIQUE (username)
) ENGINE=InnoDB;

-- =========================
-- USER_ROLES
-- =========================
CREATE TABLE user_roles (
    id BINARY(16) NOT NULL,
    user_id BINARY(16) NOT NULL,
    role_id BINARY(16) NOT NULL,
    assigned_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_user_roles PRIMARY KEY (id),
    CONSTRAINT uk_user_role_pair UNIQUE (user_id, role_id),

    CONSTRAINT fk_ur_user FOREIGN KEY (user_id)
        REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_ur_role FOREIGN KEY (role_id)
        REFERENCES roles(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- =========================
-- REFRESH TOKENS
-- =========================
CREATE TABLE refresh_tokens (
    id BINARY(16) NOT NULL,
    token VARCHAR(512) NOT NULL,
    user_id BINARY(16) NOT NULL,

    revoked BOOLEAN DEFAULT FALSE,
    expired BOOLEAN DEFAULT FALSE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL,

    CONSTRAINT pk_refresh_tokens PRIMARY KEY (id),
    CONSTRAINT uk_rt_token UNIQUE (token),
    CONSTRAINT fk_rt_user FOREIGN KEY (user_id)
        REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- ===========================
-- DATA PRE-LOADING
-- ===========================
INSERT INTO roles (id, name, description) VALUES
(UUID_TO_BIN(UUID()), 'ROLE_USER', 'Default role for all registered users'),
(UUID_TO_BIN(UUID()), 'ROLE_ADMIN', 'System administrator with full access');