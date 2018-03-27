/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:5306
Source Database       : photos

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-26 09:08:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` varchar(50) NOT NULL COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `create_date` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `phone` varchar(50) DEFAULT NULL COMMENT '电话',
  `login_name` varchar(75) DEFAULT NULL COMMENT '登录名',
  `password` varchar(75) DEFAULT NULL COMMENT '登录密码',
  `user_type` varchar(1) DEFAULT NULL COMMENT '用户类型，普通用户为0，管理员为1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录信息表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'root', 'm', '2017-10-31', null, 'root', '123456', '1');
INSERT INTO `tb_user` VALUES ('2', 'test', 'w', '2017-12-12', null, 'test', '123456', '0');
