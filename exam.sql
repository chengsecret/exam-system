/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 5.6.46 : Database - examsystem2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`examsystem2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `examsystem2`;

/*Table structure for table `answer` */

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer` (
  `aid` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `commit` varchar(255) DEFAULT NULL COMMENT '学生的答案',
  `flag` int(1) DEFAULT '0' COMMENT '是否批改',
  `score` int(3) DEFAULT '0' COMMENT '该题的得分',
  `gid` int(6) DEFAULT NULL COMMENT '哪个人在哪张考卷的回答',
  `qid` int(6) DEFAULT NULL COMMENT '哪道题目',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `answer` */

insert  into `answer`(`aid`,`commit`,`flag`,`score`,`gid`,`qid`) values 
(10,'as',1,0,1115,1),
(11,'sa',1,0,1115,2),
(12,'asas',0,0,1115,3),
(13,'a',1,2,1116,4),
(14,'fine',0,0,1116,7),
(15,'1',1,0,1117,1),
(16,'q',0,0,1117,3),
(17,'q',1,0,1117,2),
(18,'a',1,2,1118,4),
(19,'b',1,3,1118,5),
(20,'a',0,0,1118,6),
(21,'s',0,0,1118,7);

/*Table structure for table `grade` */

DROP TABLE IF EXISTS `grade`;

CREATE TABLE `grade` (
  `gid` int(6) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` int(6) DEFAULT NULL COMMENT '谁的成绩',
  `pid` int(6) DEFAULT NULL COMMENT '考的哪张试卷',
  `score` int(3) DEFAULT '0' COMMENT '得分',
  `flag` int(1) DEFAULT '0' COMMENT '0没批改，1批改',
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=1119 DEFAULT CHARSET=utf8;

/*Data for the table `grade` */

insert  into `grade`(`gid`,`uid`,`pid`,`score`,`flag`) values 
(1115,6,1,0,0),
(1116,6,2,2,0),
(1117,2,1,0,0),
(1118,7,2,5,0);

/*Table structure for table `papper` */

DROP TABLE IF EXISTS `papper`;

CREATE TABLE `papper` (
  `pid` int(6) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(128) DEFAULT NULL COMMENT '试卷名',
  `creator` varchar(10) DEFAULT NULL COMMENT '试卷发布者',
  `time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `mins` int(10) DEFAULT NULL COMMENT '时长，分钟',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `papper` */

insert  into `papper`(`pid`,`title`,`creator`,`time`,`mins`) values 
(1,'高等数学','王老师','2020-12-23 21:16:23',60),
(2,'大学英语','李老师','2020-12-23 21:16:25',120);

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `qid` int(6) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(6) DEFAULT NULL COMMENT '所属试卷',
  `type` int(2) DEFAULT NULL COMMENT '类型，0为选择，1为其它',
  `content` varchar(256) DEFAULT NULL COMMENT '题目内容',
  `key` varchar(255) DEFAULT NULL COMMENT '答案',
  `score` int(3) DEFAULT '0' COMMENT '分值',
  PRIMARY KEY (`qid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `question` */

insert  into `question`(`qid`,`pid`,`type`,`content`,`key`,`score`) values 
(1,1,0,'1+1=？   A:2   B:3   C:4   D:5','a',3),
(2,1,0,'4-1=?   A:2   B:3   C:4   D:5','B',4),
(3,1,1,'你爱高数吗',NULL,10),
(4,2,0,'hell_     A:o   B:b   C:c','a',2),
(5,2,0,'how ___ you?   A:is B:are   C:am','B',3),
(6,2,1,'do you like English?',NULL,5),
(7,2,1,'how are you?',NULL,6);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(6) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `salt` varchar(128) DEFAULT NULL COMMENT '盐',
  `password` varchar(40) DEFAULT NULL COMMENT '密码',
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `num` varchar(10) DEFAULT NULL COMMENT '学号',
  `classroom` varchar(10) DEFAULT NULL COMMENT '班级',
  `role` varchar(10) DEFAULT 'student' COMMENT '角色',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`salt`,`password`,`name`,`num`,`classroom`,`role`) values 
(2,'JFSEZPHW','cc018c563c17954c44a60ce66cdc82e3','admin','admin','88888888','teacher'),
(6,'X73YBNVL','a624236c05ffedb1ce8ae0f4e484e98d','66','18051212','18052312','student'),
(7,'32GHD8ZU','d2f5a3228fdcc228f3fbc33fde0aa115','哈哈','18051213','18052312','student');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
