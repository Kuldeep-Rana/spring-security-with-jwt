INSERT INTO roles (id, name) VALUES
(6, 'ROLE_USER'),
(8, 'ROLE_ADMIN'),
(10, 'ROLE_MANAGER');

INSERT INTO users (id, password, username) VALUES
(5, '$2a$10$MIGCZemtEULXpc0c9exGru0fFM/wuczq8bKw6cqbn7c0WlakvHFbu', 'user1'),
(7, '$2a$10$wSOHjk3yzRPVgYzrcnkoaOCius8x20q4Lj3.2DAn64vaR/eVz5OMq', 'admin'),
(9, '$2a$10$7TXN4FAp1teTKgauZw3hduBEYwX6WkJYTlKgqpkQcypfgj5sj2oyi', 'manager');

INSERT INTO USER_ROLES (USER_ID, ROLE_ID) VALUES
(5, 6),
(7, 8),
(9, 10);

