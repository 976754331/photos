/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:5306
Source Database       : photos

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-26 09:08:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_user_session
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_session`;
CREATE TABLE `tb_user_session` (
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `session_id` varchar(50) DEFAULT NULL COMMENT '每次登陆标识会话id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录存储会话表';

-- ----------------------------
-- Records of tb_user_session
-- ----------------------------
INSERT INTO `tb_user_session` VALUES ('1', '7058457115A1FBBE5E570B2CA982BA44');
INSERT INTO `tb_user_session` VALUES ('2', '80BA1CBADEA7B62CED3D9799FFDAA483');
