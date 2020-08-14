DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` INT(5) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` VARCHAR(10) NOT NULL DEFAULT '' COMMENT '姓名',
    `sex` TINYINT(1) NOT NULL COMMENT '性别',
    `age` INT(5) NOT NULL COMMENT '年龄',
    `phone` VARCHAR(15) NOT NULL UNIQUE COMMENT '手机号',
    `is_active` TINYINT(1) NOT NULL COMMENT '是否激活，0：未激活，1：已激活',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `last_update_time` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
