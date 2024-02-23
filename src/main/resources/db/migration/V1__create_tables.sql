-- db/migration/V1__create_tables.sql
USE db_game_api;

CREATE TABLE IF NOT EXISTS characters (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(50) NOT NULL,
                            pv INT,
                            atq INT,
                            def INT,
                            speed INT
);

CREATE TABLE IF NOT EXISTS items (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                       type ENUM('HELMET', 'ARMOR', 'ONE_HAND_WEAPON', 'TWO_HAND_WEAPON', 'SHIELD', 'RING', 'SHOES', 'CONSUMABLE') NOT NULL,
                        description TEXT NOT NULL,
                        bonus TINYINT NOT NULL,
                        stat ENUM('PV', 'ATQ', 'DEF', 'SPEED')
);

CREATE TABLE IF NOT EXISTS slots (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                        type ENUM('HEAD', 'CHEST', 'LEFT_HAND', 'RIGHT_HAND', 'FINGER', 'FOOT') NOT NULL,
                        item_id BIGINT,
                        FOREIGN KEY (item_id) REFERENCES items(id)
);
