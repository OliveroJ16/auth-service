-- =========================
-- ALTER REFRESH TOKEN COLUMN TYPE
-- =========================
ALTER TABLE refresh_tokens DROP INDEX token;
ALTER TABLE refresh_tokens MODIFY COLUMN token TEXT NOT NULL;