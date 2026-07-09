/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : wifp

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2025-08-22 10:42:15
*/

DROP DATABASE IF EXISTS `wifp`;
CREATE DATABASE `wifp` DEFAULT CHARACTER SET utf8;
USE `wifp`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userName` char(20) NOT NULL,
  `nickname` char(10) NOT NULL,
  `password` char(32) NOT NULL,
  `telephone` char(11) NOT NULL,
  `photo` char(100) NOT NULL DEFAULT 'default.jpg',
  `isAuthor` char(1) NOT NULL DEFAULT 'N',
  `brief` char(24) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `noteID` int(11) NOT NULL AUTO_INCREMENT,
  `author` char(20) NOT NULL,
  `noteTitle` char(100) NOT NULL,
  `noteContent` text NOT NULL,
  `visit` int(11) NOT NULL DEFAULT '0',
  `categoryName` char(50) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`noteID`),
  KEY `note_users_fk` (`author`),
  CONSTRAINT `note_users_fk` FOREIGN KEY (`author`) REFERENCES `users` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `commentID` int(11) NOT NULL AUTO_INCREMENT,
  `noteID` int(11) NOT NULL,
  `userName` char(20) NOT NULL,
  `commentTitle` char(100) NOT NULL,
  `commentContent` text NOT NULL,
  `remoteIP` char(50) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`commentID`),
  KEY `comment_note_fk` (`noteID`),
  KEY `comment_users_fk` (`userName`),
  CONSTRAINT `comment_note_fk` FOREIGN KEY (`noteID`) REFERENCES `note` (`noteID`),
  CONSTRAINT `comment_users_fk` FOREIGN KEY (`userName`) REFERENCES `users` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tagName` char(50) NOT NULL,
  `noteID` int(11) NOT NULL,
  PRIMARY KEY (`tagName`,`noteID`),
  KEY `tag_note_fk` (`noteID`),
  CONSTRAINT `tag_note_fk` FOREIGN KEY (`noteID`) REFERENCES `note` (`noteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('administrator', '系统管理员', '4f208e87dbf1f6ded475ec7a7c8dea87', '00000000001', 'default.jpg', 'Y', '负责系统管理与权限分配', '2025-07-15 20:22:16');
INSERT INTO `users` VALUES ('manager01', '安全经理', '314957829776de8db76d5a8df5e535b1', '00000000002', 'default.jpg', 'Y', '处理隐患反馈与审批', '2025-07-15 20:22:16');
INSERT INTO `users` VALUES ('staff01', '设备巡检员', '314957829776de8db76d5a8df5e535b1', '00000000003', 'default.jpg', 'N', '负责设备日常巡检上报', '2025-07-15 20:22:16');
INSERT INTO `users` VALUES ('staff02', '安全员', '314957829776de8db76d5a8df5e535b1', '00000000004', 'default.jpg', 'N', '负责安全隐患排查上报', '2025-07-15 20:22:16');

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES ('1', 'staff01', '三号机床异响巡检报告', '今日上午巡检发现三号机床存在异常异响，建议及时检修', '6', '设备巡检报告', '2025-07-15 09:10:16', null);
INSERT INTO `note` VALUES ('2', 'staff02', '车间消防通道堵塞隐患', '车间A区消防通道被物料占用，存在安全隐患', '10', '安全隐患上报', '2025-07-15 10:20:16', null);
INSERT INTO `note` VALUES ('3', 'staff01', '关于增加设备巡检频次的建议', '建议对老旧设备增加巡检频次，预防故障发生', '8', '安全改进建议', '2025-07-15 14:30:16', null);
INSERT INTO `note` VALUES ('4', 'staff02', '机床异响处理跟进', '已按反馈要求停机，等待维修人员到场', '10', '巡检异常跟进', '2025-07-15 16:00:16', '2025-07-15 16:05:16');
INSERT INTO `note` VALUES ('5', 'staff01', '老旧机床维护需求', '编号3-5号机床需更换轴承，建议本周安排', '42', '设备维护需求', '2025-07-15 17:10:16', null);

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', 'manager01', '关于机床异常的处理意见', '已安排维修人员今日下午检修，请关注进展', '192.168.1.101', '2025-07-15 15:30:16');
INSERT INTO `comment` VALUES ('2', '2', 'manager01', '关于消防通道堵塞的整改要求', '立即清理通道，1小时内反馈结果', '192.168.1.101', '2025-07-15 16:40:16');

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('隐患等级-中等', '1');
INSERT INTO `tag` VALUES ('隐患等级-重大', '2');
INSERT INTO `tag` VALUES ('隐患等级-轻微', '3');
INSERT INTO `tag` VALUES ('隐患等级-无', '4');
INSERT INTO `tag` VALUES ('隐患等级-轻微', '5');

SET FOREIGN_KEY_CHECKS=1;
