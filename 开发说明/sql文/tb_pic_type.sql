/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:5306
Source Database       : photos

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-26 09:07:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_pic_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_pic_type`;
CREATE TABLE `tb_pic_type` (
  `type_id` varchar(50) NOT NULL COMMENT '主键id',
  `type_name` varchar(50) DEFAULT NULL COMMENT '分类名',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '上级类型id',
  `type_comment` varchar(100) DEFAULT NULL COMMENT '分类说明',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片类型表';

-- ----------------------------
-- Records of tb_pic_type
-- ----------------------------
INSERT INTO `tb_pic_type` VALUES ('101', 'Peter', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('102', 'Selinely', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('103', 'Glolin', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('104', 'Anlen', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('2', '我的足迹', null, null, '1');
INSERT INTO `tb_pic_type` VALUES ('201', '成都', '2', null, '1');
INSERT INTO `tb_pic_type` VALUES ('203', '北京', '2', null, '1');
INSERT INTO `tb_pic_type` VALUES ('204', '西宁', '2', null, '1');
INSERT INTO `tb_pic_type` VALUES ('205', '兰州', '2', null, '1');
INSERT INTO `tb_pic_type` VALUES ('206', '南宁', '2', null, '1');
INSERT INTO `tb_pic_type` VALUES ('3', '在路上', null, null, '1');
INSERT INTO `tb_pic_type` VALUES ('302', '青海湖', '3', null, '1');
INSERT INTO `tb_pic_type` VALUES ('303', '华山', '3', null, '1');
INSERT INTO `tb_pic_type` VALUES ('32d6c6d441a04d1d910683b67ef870c0', 'asd', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('4', '其他...', null, null, '1');
INSERT INTO `tb_pic_type` VALUES ('402', '拓展', '4', null, '1');
INSERT INTO `tb_pic_type` VALUES ('5', '本地文件扫描', null, '扫描本地文件并上传', '1');
INSERT INTO `tb_pic_type` VALUES ('55f0ca59838a47628dbe4a755c1769e2', 'weqwe', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('6d51606b9a52491d92efd03a589b48fe', 'test111', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('793a8f031b554d719294101f5a079bb9', '1123', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('8ac817650dcc474eb5d1e14cb84c4ff1', '123', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('93888ab218e64ba5ad390c0cf6098176', 'tes11', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('952dfa157fad4041aa0dd690bf40f066', '123123', null, null, '2');
INSERT INTO `tb_pic_type` VALUES ('9a8aee5b39984c5cb85e291290f562d7', 'test', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('b4e933647fcf41cc8733a56a2e5f2447', '12sq', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('c6c3c8f477ca4eb98f57288412391520', 'asdasd', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('d005aa07b1d44a6db93f3af94bae0d4d', 'as1', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('e0fc727d3e364fe48fc036f8f5dd241c', 'adas', '1', null, '1');
INSERT INTO `tb_pic_type` VALUES ('eba7e25463f74c159e3ed6ca3ea833bd', 'asd', '1', null, '1');
