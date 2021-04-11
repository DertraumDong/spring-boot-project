DROP TABLE IF EXISTS `cat_email_log`;
CREATE TABLE `cat_email_log`
(
    `id`             int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `email_context`  varchar(1000) NOT NULL COMMENT '日志',
    `create_user_id` varchar(20) NOT NULL COMMENT '创建人',
    `create_time`    datetime DEFAULT NULL COMMENT '创建时间',
    `update_user_id` varchar(20) DEFAULT NULL COMMENT '修改人',
    `update_time`    datetime DEFAULT NULL COMMENT '修改时间',
    `version`    int(10) DEFAULT 1 NULL COMMENT '版本号',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci COMMENT ='测试日志表';