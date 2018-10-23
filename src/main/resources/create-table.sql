CREATE DATABASE IF NOT EXISTS `log_database`;

CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(50) NOT NULL,
  `request` varchar(50) NOT NULL,
  `status` SMALLINT NOT NULL,
  `user_agent` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE table block_log (
	`id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ip` varchar(50) NOT NULL,
  `comment` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);