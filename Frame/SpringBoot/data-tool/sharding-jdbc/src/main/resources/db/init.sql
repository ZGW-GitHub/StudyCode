-- 数据库 sharding-jdbc-db0
DROP DATABASE IF EXISTS `sharding-jdbc-db0`;
CREATE DATABASE `sharding-jdbc-db0` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `sharding-jdbc-db0`;

DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0`
(
    `id`   bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(25) DEFAULT NULL,
    `age`  int(5)      DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1`
(
    `id`   bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(25) DEFAULT NULL,
    `age`  int(5)      DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


-- 数据库 sharding-jdbc-db1
DROP DATABASE IF EXISTS `sharding-jdbc-db1`;
CREATE DATABASE `sharding-jdbc-db1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `sharding-jdbc-db1`;

DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` (
    `id`   bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(25) DEFAULT NULL,
    `age`  int(5)      DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` (
    `id`   bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(25) DEFAULT NULL,
    `age`  int(5)      DEFAULT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
