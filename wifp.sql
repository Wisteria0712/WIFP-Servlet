-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: wifp
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment`
(
    `commentID`      int       NOT NULL AUTO_INCREMENT,
    `noteID`         int       NOT NULL,
    `userName`       char(20)  NOT NULL,
    `commentTitle`   char(100) NOT NULL,
    `commentContent` text      NOT NULL,
    `remoteIP`       char(50)  NOT NULL,
    `createTime`     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`commentID`),
    KEY              `comment_note_fk` (`noteID`),
    KEY              `comment_users_fk` (`userName`),
    CONSTRAINT `comment_note_fk` FOREIGN KEY (`noteID`) REFERENCES `note` (`noteID`),
    CONSTRAINT `comment_users_fk` FOREIGN KEY (`userName`) REFERENCES `users` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK
TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`commentID`, `noteID`, `userName`, `commentTitle`, `commentContent`, `remoteIP`, `createTime`)
VALUES (1, 1, 'manager01', '关于机床异常的处理意见', '已安排维修人员今日下午检修，请关注进展', '192.168.1.101',
        '2025-07-15 07:30:16'),
       (2, 2, 'manager01', '关于消防通道堵塞的整改要求', '立即清理通道，1小时内反馈结果', '192.168.1.101',
        '2025-07-15 08:40:16');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `note`
(
    `noteID`       int       NOT NULL AUTO_INCREMENT,
    `author`       char(20)  NOT NULL,
    `noteTitle`    char(100) NOT NULL,
    `noteContent`  text      NOT NULL,
    `visit`        int       NOT NULL DEFAULT '0',
    `categoryName` char(50)  NOT NULL,
    `createTime`   timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime`   timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`noteID`),
    KEY            `note_users_fk` (`author`),
    CONSTRAINT `note_users_fk` FOREIGN KEY (`author`) REFERENCES `users` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK
TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` (`noteID`, `author`, `noteTitle`, `noteContent`, `visit`, `categoryName`, `createTime`, `updateTime`)
VALUES (1, 'staff01', '三号机床异响巡检报告', '今日上午巡检发现三号机床存在异常异响，建议及时检修', 6, '设备巡检报告',
        '2025-07-15 01:10:16', NULL),
       (2, 'staff02', '车间消防通道堵塞隐患', '车间A区消防通道被物料占用，存在安全隐患', 10, '安全隐患上报',
        '2025-07-15 02:20:16', NULL),
       (3, 'staff01', '关于增加设备巡检频次的建议', '建议对老旧设备增加巡检频次，预防故障发生', 8, '安全改进建议',
        '2025-07-15 06:30:16', NULL),
       (4, 'staff02', '机床异响处理跟进', '已按反馈要求停机，等待维修人员到场', 10, '巡检异常跟进',
        '2025-07-15 08:00:16', '2025-07-15 08:05:16'),
       (5, 'staff01', '老旧机床维护需求', '编号3-5号机床需更换轴承，建议本周安排', 42, '设备维护需求',
        '2025-07-15 09:10:16', NULL);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag`
(
    `tagName` char(50) NOT NULL,
    `noteID`  int      NOT NULL,
    PRIMARY KEY (`tagName`, `noteID`),
    KEY       `tag_note_fk` (`noteID`),
    CONSTRAINT `tag_note_fk` FOREIGN KEY (`noteID`) REFERENCES `note` (`noteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK
TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` (`tagName`, `noteID`)
VALUES ('隐患等级-中等', 1),
       ('隐患等级-重大', 2),
       ('隐患等级-轻微', 3),
       ('隐患等级-无', 4),
       ('隐患等级-轻微', 5);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users`
(
    `userName`   char(20)  NOT NULL,
    `nickname`   char(10)  NOT NULL,
    `password`   char(32)  NOT NULL,
    `telephone`  char(11)  NOT NULL,
    `photo`      char(100) NOT NULL DEFAULT 'default.jpg',
    `isAuthor`   char(1)   NOT NULL DEFAULT 'N',
    `brief`      char(24)  NOT NULL,
    `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK
TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`userName`, `nickname`, `password`, `telephone`, `photo`, `isAuthor`, `brief`, `createTime`)
VALUES ('admin', '系统管理员', 'e10adc3949ba59abbe56e057f20f883e', '00000000001', 'default.jpg', 'Y',
        '负责系统管理与权限分配', '2025-07-15 12:22:16'),
       ('manager01', '安全经理', 'e10adc3949ba59abbe56e057f20f883e', '00000000002', 'default.jpg', 'Y',
        '处理隐患反馈与审批', '2025-07-15 12:22:16'),
       ('staff01', '设备巡检员', 'e10adc3949ba59abbe56e057f20f883e', '00000000003', 'default.jpg', 'N',
        '负责设备日常巡检上报', '2025-07-15 12:22:16'),
       ('staff02', '安全员', 'e10adc3949ba59abbe56e057f20f883e', '00000000004', 'default.jpg', 'N',
        '负责安全隐患排查上报', '2025-07-15 12:22:16'),
       ('test', 'wisteria', 'e10adc3949ba59abbe56e057f20f883e', '19875421599', 'default.jpg', 'N', '测试用户',
        '2026-07-09 03:25:43');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-09 12:46:13
