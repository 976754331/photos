/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:5306
Source Database       : photos

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-26 09:07:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_disk_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_disk_record`;
CREATE TABLE `tb_disk_record` (
  `record_id` varchar(50) NOT NULL COMMENT '记录id，主键',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `file_path` varchar(155) DEFAULT NULL COMMENT '上传本地磁盘文件路径',
  `uplode_date` varchar(50) DEFAULT NULL COMMENT '上传时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个人上传本地文件记录表';

-- ----------------------------
-- Records of tb_disk_record
-- ----------------------------
