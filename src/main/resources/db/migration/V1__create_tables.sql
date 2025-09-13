-- Создание таблицы users --
CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100),
    email VARCHAR(100),
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    enabled BOOLEAN
);

-- Создание таблицы authority --
CREATE TABLE authority (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    authority VARCHAR(50),
    FOREIGN KEY (username) REFERENCES users(username)
);
