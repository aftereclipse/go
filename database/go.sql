/*
MySQL Data Transfer
Source Host: localhost
Source Database: go
Target Host: localhost
Target Database: go
Date: 2013/6/4 21:33:43
*/

-- 数据库表文件
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- 后台管理表
-- ----------------------------
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uname` varchar(30) NOT NULL DEFAULT '',
  `passwd` varchar(30) NOT NULL DEFAULT '',
  `timetag` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for category
-- 类别表
-- ----------------------------
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `click` bigint(20) DEFAULT '0',
  `timetag` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hotarticle
-- 热门推荐文章表
-- ----------------------------
CREATE TABLE `hotarticle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `link` varchar(128) DEFAULT NULL,
  `click` bigint(20) DEFAULT '0',
  `timetag` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hotblog
-- 热门博客表
-- ----------------------------
CREATE TABLE `hotblog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `link` varchar(128) DEFAULT NULL,
  `timetag` date DEFAULT NULL,
  `click` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lcrelation
-- 类别 网站关系表
-- ----------------------------
CREATE TABLE `lcrelation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cid` bigint(20) NOT NULL DEFAULT '0',
  `lid` bigint(20) NOT NULL DEFAULT '0',
  `timetag` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for link
-- 网站表
-- ----------------------------
CREATE TABLE `link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) NOT NULL DEFAULT '',
  `title` varchar(128) DEFAULT NULL,
  `content` varchar(128) DEFAULT NULL,
  `timetag` date DEFAULT NULL,
  `click`  bigint(20) DEFAULT '0',
  `color` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- 用户表
-- ----------------------------
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(30) NOT NULL DEFAULT '',
  `pwd` varchar(30) NOT NULL DEFAULT '',
  `timetag` date DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
