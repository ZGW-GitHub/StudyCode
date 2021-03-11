DROP DATABASE IF EXISTS `redis_cache`;
CREATE DATABASE `redis_cache` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `redis_cache`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`   int(5) PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(25) DEFAULT NULL,
    `age`  int(5)      DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
