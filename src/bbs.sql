/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2016-01-11 22:53:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_u_id` int(11) NOT NULL,
  `r_t_id` int(11) NOT NULL,
  `r_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `r_contents` varchar(255) NOT NULL,
  PRIMARY KEY (`r_id`),
  KEY `r_t_id` (`r_t_id`),
  KEY `r_u_id` (`r_u_id`),
  CONSTRAINT `r_t_id` FOREIGN KEY (`r_t_id`) REFERENCES `topic` (`t_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `r_u_id` FOREIGN KEY (`r_u_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(50) NOT NULL,
  `s_number` int(11) NOT NULL DEFAULT '0',
  `s_info` varchar(100) NOT NULL,
  `s_u_id` int(11) NOT NULL,
  PRIMARY KEY (`s_id`),
  KEY `s_u_id` (`s_u_id`),
  CONSTRAINT `s_u_id` FOREIGN KEY (`s_u_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('1', 'html', '0', 'Html课程', '1');
INSERT INTO `section` VALUES ('2', 'mysql', '0', 'Mysql课程', '1');
INSERT INTO `section` VALUES ('3', 'java', '0', 'Java课程', '1');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_s_id` int(11) NOT NULL,
  `t_u_id` int(11) NOT NULL,
  `t_contents` varchar(255) NOT NULL,
  `t_number` int(11) NOT NULL DEFAULT '0',
  `t_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `t_title` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`t_id`),
  KEY `t_s_id` (`t_s_id`),
  KEY `t_u_id` (`t_u_id`),
  CONSTRAINT `t_s_id` FOREIGN KEY (`t_s_id`) REFERENCES `section` (`s_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_u_id` FOREIGN KEY (`t_u_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(10) NOT NULL,
  `u_password` varchar(10) NOT NULL,
  `u_status` int(11) DEFAULT '0',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', 'a123', '1');
INSERT INTO `users` VALUES ('2', 'user', 'a123', '0');
INSERT INTO `users` VALUES ('3', 'u', 'a123', '1');
