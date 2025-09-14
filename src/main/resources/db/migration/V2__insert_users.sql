-- Вставка пользователей --
INSERT INTO users (username, password, email, firstname, lastname, enabled)
VALUES ('admin', '$2a$12$OwzzwI7I8l1uo7vUD9Ff.eAGprZxaaQBq.qqW1nqvpegS2tU9yYQK', 'nikolai.horetski@gmail.com', 'admin', 'admin', true);

INSERT INTO users (username, password, email, firstname, lastname, enabled)
VALUES ('user', '$2a$12$IhpxZSbUjjm4w1s0xmTDde/ad1LPeXnJBDLnAEcIQUIDV2ly1Lnom', 'nikolai.horetski@gmail.com', 'user', 'user', true);

INSERT INTO users (username, password, email, firstname, lastname, enabled)
VALUES ('test', '$2a$12$/KZBPArANUWJXMwAIe51F.8NEMYoLxZ4Ek0Qp/0AsvehxH8yL/WBi', 'nikolai.horetski@gmail.com', 'test', 'test', true);

INSERT INTO users (username, password, email, firstname, lastname, enabled)
VALUES ('adminadmin', '$2a$12$4AwqycS2qp8nPCAfwDTqTez9U2CMj4SFKk9O7L5t7IZRb0DBdwdMu', 'svetssapeshko1996@gmail.com', 'admin', 'admin', true);

-- Вставка ролей для пользователей --
INSERT INTO authority (username, authority) VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO authority (username, authority) VALUES ('user', 'ROLE_USER');

INSERT INTO authority (username, authority) VALUES ('test', 'ROLE_USER');

INSERT INTO authority (username, authority) VALUES ('adminadmin', 'ROLE_ADMIN');
