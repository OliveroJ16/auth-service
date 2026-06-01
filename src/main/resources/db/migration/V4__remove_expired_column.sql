-- =====================================
-- V4 - Remove redundant expired column
-- =====================================

ALTER TABLE refresh_tokens
DROP COLUMN expired;