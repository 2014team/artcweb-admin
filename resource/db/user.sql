/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.1.62-community : Database - artcweb_admin
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`artcweb_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `artcweb_admin`;

/*Table structure for table `admin_user` */

DROP TABLE IF EXISTS `admin_user`;

CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新日期',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `vaild` int(11) NOT NULL DEFAULT '0' COMMENT '0:无效 1:有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `admin_user` */

insert  into `admin_user`(`id`,`user_name`,`password`,`create_date`,`update_date`,`email`,`vaild`) values (1,'admin','admin','2019-11-14 20:41:16','2019-11-14 20:41:18','',0);

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile` varchar(20) NOT NULL COMMENT '手机号码',
  `package_id` varchar(20) NOT NULL COMMENT '套餐ID',
  `current_step` varchar(50) NOT NULL COMMENT '当前步骤',
  `sort` int(11) NOT NULL COMMENT '排序',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `order` */

insert  into `order`(`id`,`mobile`,`package_id`,`current_step`,`sort`,`create_date`,`update_date`) values (1,'13537612556','110','',1,'2019-11-21 16:59:05','2019-11-21 16:59:08'),(2,'13537612556','111','',1,'2019-11-21 17:00:41','2019-11-21 17:00:43');

/*Table structure for table `pic_package` */

DROP TABLE IF EXISTS `pic_package`;

CREATE TABLE `pic_package` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `package_id` int(11) NOT NULL COMMENT '套餐ID',
  `package_name` varchar(100) NOT NULL COMMENT '套餐名称',
  `image_url` varchar(500) NOT NULL COMMENT '图片地址',
  `step` varchar(6000) NOT NULL COMMENT '执行步骤',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `pic_package` */

insert  into `pic_package`(`id`,`package_id`,`package_name`,`image_url`,`step`,`create_date`,`update_date`) values (1,110,'周杰伦','https://apizza.net/pro','assssssssss','2019-11-21 15:24:54','2019-11-21 15:24:56'),(2,111,'蔡依林','https://apizza.net/pro','1111111111','2019-11-21 15:25:18','2019-11-21 15:25:20'),(3,113,'张德华','https://apizza.net/pro','11111','2019-11-21 15:59:19','2019-11-21 15:59:19');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`mobile`,`sort`,`create_date`,`update_date`) values (1,'13537612557',1,'2019-11-21 16:37:38','2019-11-21 16:37:38'),(2,'13537612558',2,'2019-11-21 16:30:10','2019-11-21 16:30:12');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
