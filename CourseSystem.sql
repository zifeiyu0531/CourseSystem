/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.13 : Database - coursesystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`coursesystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `coursesystem`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teachername` varchar(5) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `course_ibfk_1` (`teachername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`name`,`teachername`,`time`,`location`) values ('数据结构','周老师','星期一14：00-16:00','二号教学楼310'),('计算机基础','李老师','星期三8：00-10:00','一号教学楼501'),('面向对象程序设计','孙老师','星期二14：00-15:30','三号教学楼407'),('高等数学','赵老师','星期三14：00-16:00','一号教学楼306');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` varchar(15) NOT NULL,
  `name` varchar(5) DEFAULT NULL,
  `class` varchar(10) DEFAULT NULL,
  `course1` varchar(10) DEFAULT NULL,
  `course2` varchar(10) DEFAULT NULL,
  `course3` varchar(10) DEFAULT NULL,
  `course4` varchar(10) DEFAULT NULL,
  `course5` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course1` (`course1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`class`,`course1`,`course2`,`course3`,`course4`,`course5`) values ('1234','1234','软工一班','','','','',''),('2017302500001','张三','计算机一班','高等数学','计算机基础','数据结构',NULL,NULL),('2017302500002','李四','计算机一班','高等数学','数据结构','线性代数','面向对象程序设计',NULL),('2017302500003','王五','计算机一班','计算机基础','数据结构','线性代数',NULL,NULL),('2017302500004','赵六','计算机一班','高等数学','面向对象程序设计',NULL,NULL,NULL),('2017302600113','唐僧','计算机二班','计算机基础','面向对象程序设计','线性代数',NULL,NULL),('2017302600253','孙悟空','计算机二班','高等数学','数据结构','面向对象程序设计',NULL,NULL),('2017302612321','猪八戒','计算机二班','线性代数','计算机基础',NULL,NULL,NULL),('2017302632654','白龙马','计算机二班','高等数学','计算机基础','面向对象程序设计','数据结构','线性代数'),('2017302700023','关羽','软工一班','高等数学','面向对象程序设计','线性代数',NULL,NULL),('2017302700321','刘备','软工一班','计算机基础','数据结构',NULL,NULL,NULL),('2017302703001','张飞','软工一班','高等数学','面向对象程序设计','数据结构','线性代数',NULL),('2017302722123','诸葛亮','软工一班','数据结构','线性代数',NULL,NULL,NULL),('2017302800002','查理','软工二班','高等数学','计算机基础','数据结构',NULL,NULL),('2017302800232','杰克','软工二班','计算机基础','线性代数','面向对象程序设计','高等数学',NULL),('2017302800234','布莱恩','软工二班','高等数学','计算机基础',NULL,NULL,NULL),('2017302865223','狗剩','软工二班','面向对象程序设计','数据结构','线性代数',NULL,NULL);

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `teachername` varchar(5) NOT NULL,
  `course` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '无',
  `school` varchar(10) DEFAULT NULL,
  `title` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`teachername`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`teachername`,`course`,`school`,`title`) values ('周老师','数据结构','计算机学院','教授'),('孙老师','面向对象程序设计','计算机学院','副教授'),('李老师','计算机基础','计算机学院','教授'),('王老师','线性代数','数学学院','副教授'),('赵老师','高等数学','数学学院','教授');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`name`,`password`) values ('123','123');

/*Table structure for table `数据结构` */

DROP TABLE IF EXISTS `数据结构`;

CREATE TABLE `数据结构` (
  `studentid` varchar(15) NOT NULL,
  `studentname` varchar(5) DEFAULT NULL,
  `class` varchar(10) DEFAULT NULL,
  `grade` int(3) DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `数据结构` */

insert  into `数据结构`(`studentid`,`studentname`,`class`,`grade`) values ('2017302500001','张三','计算机一班',93),('2017302500002','李四','计算机一班',88),('2017302600253','孙悟空','计算机二班',77),('2017302632654','白龙马','计算机二班',89),('2017302700321','刘备','软工一班',66),('2017302703001','张飞','软工一班',78),('2017302722123','诸葛亮','软工一班',81),('2017302800002','查理','软工二班',90),('2017302865223','狗剩','软工二班',80);

/*Table structure for table `计算机一班` */

DROP TABLE IF EXISTS `计算机一班`;

CREATE TABLE `计算机一班` (
  `studentid` varchar(15) NOT NULL,
  `studentname` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `计算机一班` */

insert  into `计算机一班`(`studentid`,`studentname`) values ('123','123'),('2017302500001','张三'),('2017302500002','李四'),('2017302500003','王五'),('2017302500004','赵六');

/*Table structure for table `计算机二班` */

DROP TABLE IF EXISTS `计算机二班`;

CREATE TABLE `计算机二班` (
  `studentid` varchar(15) NOT NULL,
  `studentname` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `计算机二班` */

insert  into `计算机二班`(`studentid`,`studentname`) values ('2017302600113','唐僧'),('2017302600253','孙悟空'),('2017302612321','猪八戒'),('2017302632654','白龙马');

/*Table structure for table `计算机基础` */

DROP TABLE IF EXISTS `计算机基础`;

CREATE TABLE `计算机基础` (
  `studentid` varchar(15) NOT NULL,
  `studentname` varchar(5) DEFAULT NULL,
  `class` varchar(10) DEFAULT NULL,
  `grade` int(3) DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `计算机基础` */

insert  into `计算机基础`(`studentid`,`studentname`,`class`,`grade`) values ('2017302500001','张三','计算机一班',87),('2017302500003','王五','计算机一班',93),('2017302600113','唐僧','计算机二班',82),('2017302612321','猪八戒','计算机二班',80),('2017302632654','白龙马','计算机二班',77),('2017302700321','刘备','软工一班',91),('2017302800002','查理','软工二班',63),('2017302800232','杰克','软工二班',79),('2017302800234','布莱恩','软工二班',88);

/*Table structure for table `软工一班` */

DROP TABLE IF EXISTS `软工一班`;

CREATE TABLE `软工一班` (
  `studentid` varchar(15) NOT NULL,
  `studentname` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `软工一班` */

insert  into `软工一班`(`studentid`,`studentname`) values ('1234','1234'),('2017302700023','关羽'),('2017302700321','刘备'),('2017302703001','张飞'),('2017302722123','诸葛亮');

/*Table structure for table `软工二班` */

DROP TABLE IF EXISTS `软工二班`;

CREATE TABLE `软工二班` (
  `studentid` varchar(15) NOT NULL,
  `studentname` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `软工二班` */

insert  into `软工二班`(`studentid`,`studentname`) values ('2017302800002','查理'),('2017302800232','杰克'),('2017302800234','布莱恩'),('2017302865223','狗剩');

/*Table structure for table `面向对象程序设计` */

DROP TABLE IF EXISTS `面向对象程序设计`;

CREATE TABLE `面向对象程序设计` (
  `studentid` varchar(15) NOT NULL,
  `studentname` varchar(5) DEFAULT NULL,
  `class` varchar(10) DEFAULT NULL,
  `grade` int(3) DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `面向对象程序设计` */

insert  into `面向对象程序设计`(`studentid`,`studentname`,`class`,`grade`) values ('2017302500002','李四','计算机一班',77),('2017302500004','赵六','计算机一班',89),('2017302600113','唐僧','计算机二班',82),('2017302600253','孙悟空','计算机二班',91),('2017302632654','白龙马','计算机二班',64),('2017302700023','关羽','软工一班',88),('2017302703001','张飞','软工一班',70),('2017302800232','杰克','软工二班',85),('2017302865223','狗剩','软工二班',79);

/*Table structure for table `高等数学` */

DROP TABLE IF EXISTS `高等数学`;

CREATE TABLE `高等数学` (
  `studentid` varchar(15) NOT NULL,
  `studentname` varchar(5) DEFAULT NULL,
  `class` varchar(10) DEFAULT NULL,
  `grade` int(3) DEFAULT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `高等数学` */

insert  into `高等数学`(`studentid`,`studentname`,`class`,`grade`) values ('2017302500001','张三','计算机一班',94),('2017302500002','李四','计算机一班',82),('2017302500004','王五','计算机一班',79),('2017302600253','孙悟空','计算机二班',91),('2017302632654','白龙马','计算机二班',87),('2017302700023','关羽','软工一班',68),('2017302703001','张飞','软工一班',97),('2017302800002','查理','软工二班',69),('2017302800232','杰克','软工二班',84),('2017302800234','布莱恩','软工二班',88);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
