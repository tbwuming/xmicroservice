-- ----------------------------
-- Database for xmicroservice
-- ----------------------------
CREATE DATABASE IF NOT EXISTS xmicroservice;


-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `features` varchar(255) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `singer` varchar(50) DEFAULT NULL,
  `company` varchar(100) DEFAULT NULL,
  `status` tinyint(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  FULLTEXT KEY `idx_company` (`company`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES ('1', '0000-00-00 00:00:00', '0000-00-00 00:00:00', null, '后来', '刘若英', '北京人民大学', '0');
INSERT INTO `music` VALUES ('2', '0000-00-00 00:00:00', '0000-00-00 00:00:00', null, '小苹果', '筷子兄弟', '人民医院', '0');
INSERT INTO `music` VALUES ('3', '0000-00-00 00:00:00', '0000-00-00 00:00:00', null, '算你狠', '陈小春', '北京大学', '0');
INSERT INTO `music` VALUES ('4', '0000-00-00 00:00:00', '0000-00-00 00:00:00', null, '朋友', '周华健', '北京大学', '0');

