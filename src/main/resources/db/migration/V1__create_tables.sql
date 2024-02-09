-- db/migration/V1__create_tables.sql
USE db_game_api;

CREATE TABLE characters (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(50) NOT NULL,
                            pv INT,
                            atq INT,
                            def INT,
                            speed INT
);

CREATE TABLE items (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                       type ENUM('HELMET', 'ARMOR', 'ONE_HAND_WEAPON', 'TWO_HAND_WEAPON', 'SHIELD', 'RING', 'SHOES', 'CONSUMABLE') NOT NULL
);

CREATE TABLE slots (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(50) NOT NULL,
                        type ENUM('HEAD', 'CHEST', 'LEFT_HAND', 'RIGHT_HAND', 'FINGER', 'FOOT') NOT NULL,
                        item_id BIGINT,
                        FOREIGN KEY (item_id) REFERENCES items(id)
);
