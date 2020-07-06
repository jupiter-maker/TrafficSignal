/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.49 : Database - trafficsignal
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`trafficsignal` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `trafficsignal`;

/*Table structure for table `ts_dd` */

DROP TABLE IF EXISTS `ts_dd`;

CREATE TABLE `ts_dd` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '大队主键',
  `dd_name` varchar(20) NOT NULL COMMENT '大队名称',
  `dd_create` bigint(20) DEFAULT NULL COMMENT '大队创建时间',
  `dd_modified` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `dd_resp` varchar(10) NOT NULL COMMENT '大队负责人',
  `dd_resp_phone` varchar(20) NOT NULL COMMENT '大队负责人电话',
  `dd_resp_address` varchar(30) DEFAULT NULL COMMENT '大队负责人地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `ts_dl` */

DROP TABLE IF EXISTS `ts_dl`;

CREATE TABLE `ts_dl` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '道路id',
  `dl_name` varchar(10) NOT NULL COMMENT '道路名称',
  `dl_dd_id` int(11) NOT NULL COMMENT '道路所属大队id',
  `dl_resp` varchar(10) DEFAULT NULL COMMENT '道路负责人',
  `dl_resp_phone` varchar(20) DEFAULT NULL COMMENT '道路负责人电话',
  `dl_desc` varchar(20) DEFAULT NULL COMMENT '道路详情信息',
  `dl_create` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `dl_modified` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Table structure for table `ts_fa` */

DROP TABLE IF EXISTS `ts_fa`;

CREATE TABLE `ts_fa` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '方案主键',
  `fa_name` varchar(20) NOT NULL COMMENT '方案名称',
  `fa_method` varchar(20) NOT NULL COMMENT '方案控制方式',
  `fa_zxw` int(11) DEFAULT NULL COMMENT '方案主相位',
  `fa_create` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `fa_modified` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `fa_xwc` int(11) DEFAULT NULL COMMENT '相位差',
  `fa_zqcd` int(11) DEFAULT NULL COMMENT '周期长度',
  `fa_zxw_name` varchar(20) DEFAULT NULL COMMENT '主相位名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `ts_is` */

DROP TABLE IF EXISTS `ts_is`;

CREATE TABLE `ts_is` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '路口信息主键',
  `is_name` varchar(20) NOT NULL COMMENT '路口名',
  `is_dd_id` int(11) NOT NULL COMMENT '所属大队id',
  `is_dl_id` int(11) NOT NULL COMMENT '所属道路id',
  `is_xh_id` int(11) NOT NULL COMMENT '所用信号机型号id',
  `is_wh_name` varchar(20) NOT NULL COMMENT '维护人员',
  `is_create` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `is_modified` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `is_xw_num` int(11) DEFAULT NULL COMMENT '相位数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8;

/*Table structure for table `ts_sd` */

DROP TABLE IF EXISTS `ts_sd`;

CREATE TABLE `ts_sd` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '时段信息主键',
  `sd_start` varchar(10) NOT NULL COMMENT '开始时间',
  `sd_end` varchar(10) DEFAULT NULL COMMENT '结束时间',
  `sd_is_id` int(11) NOT NULL COMMENT '所属路口id',
  `sd_fa_id` int(11) NOT NULL COMMENT '所用方案id',
  `sd_name` varchar(10) NOT NULL COMMENT '时段名称',
  `sd_create` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `sd_modified` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Table structure for table `ts_user` */

DROP TABLE IF EXISTS `ts_user`;

CREATE TABLE `ts_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '用户密码。MD5加密',
  `phone` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(20) DEFAULT NULL COMMENT '注册邮箱',
  `us_create` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `us_modified` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `ts_xh` */

DROP TABLE IF EXISTS `ts_xh`;

CREATE TABLE `ts_xh` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '信号机型号主键',
  `xh_name` varchar(20) NOT NULL COMMENT '信号机名称',
  `xh_create` bigint(20) DEFAULT NULL COMMENT '型号创建时间',
  `xh_modified` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `ts_xw` */

DROP TABLE IF EXISTS `ts_xw`;

CREATE TABLE `ts_xw` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '相位表主键',
  `xw_fa_id` int(11) NOT NULL COMMENT '对应方案id',
  `xw_name` varchar(20) DEFAULT NULL COMMENT '相位名称',
  `xw_green` int(11) DEFAULT '0' COMMENT '绿灯',
  `xw_yellow` int(11) DEFAULT '0' COMMENT '黄灯',
  `xw_red` int(11) DEFAULT '0' COMMENT '红灯',
  `xw_vehicle_green` int(11) DEFAULT '0' COMMENT '机动车绿闪',
  `xw_vehicle_red` int(11) DEFAULT '0' COMMENT '机动车红闪',
  `xw_walker_green` int(11) DEFAULT '0' COMMENT '行人绿闪',
  `xw_walker_red` int(11) DEFAULT '0' COMMENT '行人红闪',
  `xw_create` bigint(20) DEFAULT NULL COMMENT '相位创建时间',
  `xw_modofied` bigint(20) DEFAULT NULL COMMENT '相位更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
