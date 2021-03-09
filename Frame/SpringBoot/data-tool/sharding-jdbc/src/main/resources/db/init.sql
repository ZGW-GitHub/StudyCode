-- 数据库 db0
DROP DATABASE IF EXISTS `db0`;
CREATE DATABASE `db0` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `db0`;

DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` (
    `id`   int(11) NOT NULL,
    `name` varchar(25) DEFAULT NULL,
    `age`  int(5)      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` (
    `id` int(11) NOT NULL,
    `name` varchar(25) DEFAULT NULL,
    `age` int(5) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


-- 数据库 db1
DROP DATABASE IF EXISTS `db1`;
CREATE DATABASE `db1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `db1`;

DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` (
    `id`   int(11) NOT NULL,
    `name` varchar(25) DEFAULT NULL,
    `age`  int(5)      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` (
    `id` int(11) NOT NULL,
    `name` varchar(25) DEFAULT NULL,
    `age` int(5) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
