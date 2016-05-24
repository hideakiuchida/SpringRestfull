/*
Navicat MySQL Data Transfer

Source Server         : mysqllocal
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2016-05-20 16:29:20
*/
DROP DATABASE IF EXISTS licoreria;
CREATE DATABASE licoreria;
USE licoreria;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `ENABLED` int(11) DEFAULT NULL,
  `LASTPASSWORDRESETDATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '12345', 'admin', 'admin', '1', '2016-05-16 16:04:17');
INSERT INTO `user` VALUES ('2', 'user', '12345', 'user', 'user', '1', '2016-05-16 16:04:17');
INSERT INTO `user` VALUES ('3', 'disabled', '12345', 'user', 'user', '0', '2016-05-16 16:04:17');

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', 'ROLE_USER');
INSERT INTO `authority` VALUES ('2', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for user_authority
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `USER_ID` int(11) NOT NULL,
  `AUTHORITY_ID` int(11) NOT NULL,
  KEY `USER_ID` (`USER_ID`),
  KEY `AUTHORITY_ID` (`AUTHORITY_ID`),
  CONSTRAINT `user_authority_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `user_authority_ibfk_2` FOREIGN KEY (`AUTHORITY_ID`) REFERENCES `authority` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
INSERT INTO `user_authority` VALUES ('1', '1');
INSERT INTO `user_authority` VALUES ('1', '2');
INSERT INTO `user_authority` VALUES ('2', '1');
INSERT INTO `user_authority` VALUES ('3', '1');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `joining_date` date NOT NULL,
  `salary` double NOT NULL,
  `ssn` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ssn` (`ssn`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('2', 'Alonso', '1999-05-08', '2550', '123456');
INSERT INTO `employee` VALUES ('3', 'Alonso', '1988-05-08', '2500', '456');

-- ----------------------------
-- Table structure for token
-- ----------------------------
DROP TABLE IF EXISTS `token`;
CREATE TABLE `token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authToken` varchar(500) DEFAULT NULL,
  `issuedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `expiresOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `token_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of token
-- ----------------------------
INSERT INTO `token` VALUES ('1', 'bmucur6qj575gticas3fj61e5r', '2016-05-20 12:27:43', '2016-05-20 12:42:44', '1');
INSERT INTO `token` VALUES ('2', '7b0k1e3rm88pr8bd49180na59k', '2016-05-20 14:29:47', '2016-05-20 14:44:47', '1');
INSERT INTO `token` VALUES ('3', 'q4u10qa43cm45u54ne0mfijfc8', '2016-05-20 15:16:10', '2016-05-20 15:31:10', '1');
INSERT INTO `token` VALUES ('4', '8tcjmtil2hqdggsevu8b984eur', '2016-05-20 15:46:38', '2016-05-20 16:01:38', '1');
INSERT INTO `token` VALUES ('5', 'jqvj1d3ouo75absderrh00d3o4', '2016-05-20 15:53:50', '2016-05-20 16:08:50', '2');
INSERT INTO `token` VALUES ('6', 'cje5e533dmi64nqmosefo40f08', '2016-05-20 15:57:38', '2016-05-20 16:12:38', '1');
INSERT INTO `token` VALUES ('7', '8ubrnsesbn57u9343o7ii3n0be', '2016-05-20 15:58:35', '2016-05-20 16:13:35', '2');


