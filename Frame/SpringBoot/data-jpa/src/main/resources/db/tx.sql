CREATE TABLE `tx` (
    `id` int(5) NOT NULL AUTO_INCREMENT,
    `name` varchar(10) NOT NULL DEFAULT '',
    `create_time` timestamp NOT NULL,
    `last_update_time` timestamp NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
