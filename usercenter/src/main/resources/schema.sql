-- ----------------------------
-- Database for xmicroservice
-- ----------------------------
CREATE DATABASE IF NOT EXISTS xmicroservice;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `features` varchar(255) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  FULLTEXT KEY `idx_address` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '0000-00-00 00:00:00', '0000-00-00 00:00:00', null, 'jimmy', '20', '北京海淀人民大学', '0');
INSERT INTO `user` VALUES ('2', '0000-00-00 00:00:00', '0000-00-00 00:00:00', null, 'mary', '18', '北京大学', '0');
INSERT INTO `user` VALUES ('3', '0000-00-00 00:00:00', '0000-00-00 00:00:00', null, 'wuming', '18', '海淀', '0');

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `features` varchar(255) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  FULLTEXT KEY `idx_address` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES ('1', '0000-00-00 00:00:00', '0000-00-00 00:00:00', null, '如家', '13777807780', '北京人民大学', '0');
INSERT INTO `hotel` VALUES ('2', '0000-00-00 00:00:00', '0000-00-00 00:00:00', null, '汉庭', '13777787878', '人民医院', '0');
INSERT INTO `hotel` VALUES ('3', '0000-00-00 00:00:00', '0000-00-00 00:00:00', null, '七天', '13777777777', '北京大学', '0');