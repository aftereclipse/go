/*
MySQL Data Transfer
Source Host: localhost
Source Database: go
Target Host: localhost
Target Database: go
Date: 2013/6/4 21:33:43
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
CREATE TABLE `admin` (
  `uname` varchar(30) NOT NULL DEFAULT '',
  `passwd` varchar(30) NOT NULL DEFAULT '',
  `timetag` date DEFAULT NULL,
  PRIMARY KEY (`uname`,`passwd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for category
-- ----------------------------
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `click` int(11) DEFAULT NULL,
  `timetag` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hotarticle
-- ----------------------------
CREATE TABLE `hotarticle` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `title` varchar(128) DEFAULT NULL,
  `link` varchar(128) DEFAULT NULL,
  `timetag` date DEFAULT NULL,
  `click` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for hotblog
-- ----------------------------
CREATE TABLE `hotblog` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `title` varchar(128) DEFAULT NULL,
  `link` varchar(128) DEFAULT NULL,
  `timetag` date DEFAULT NULL,
  `click` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lcrelation
-- ----------------------------
CREATE TABLE `lcrelation` (
  `lid` bigint(20) NOT NULL DEFAULT '0',
  `cid` bigint(20) NOT NULL DEFAULT '0',
  `timetag` date DEFAULT NULL,
  PRIMARY KEY (`lid`,`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for link
-- ----------------------------
CREATE TABLE `link` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `link` varchar(128) NOT NULL DEFAULT '',
  `title` varchar(128) DEFAULT NULL,
  `text` varchar(128) DEFAULT NULL,
  `timetag` date DEFAULT NULL,
  `click` int(11) DEFAULT NULL,
  `color` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`,`link`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `uid` varchar(30) NOT NULL DEFAULT '',
  `pwd` varchar(30) NOT NULL DEFAULT '',
  `timetag` date DEFAULT NULL,
  PRIMARY KEY (`uid`,`pwd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
