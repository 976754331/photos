/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:5306
Source Database       : photos

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-26 09:07:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_comment`;
CREATE TABLE `tb_comment` (
  `comment_id` varchar(50) NOT NULL COMMENT '评论id',
  `user_id` varchar(50) DEFAULT NULL COMMENT '评论人id',
  `pic_id` varchar(50) DEFAULT NULL COMMENT '图片id',
  `comment_date` varchar(50) DEFAULT NULL COMMENT '评论时间',
  `content` text,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片评论表';

-- ----------------------------
-- Records of tb_comment
-- ----------------------------
