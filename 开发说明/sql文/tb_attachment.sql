/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:5306
Source Database       : photos

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-26 09:07:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_attachment
-- ----------------------------
DROP TABLE IF EXISTS `tb_attachment`;
CREATE TABLE `tb_attachment` (
  `attachment_id` varchar(50) NOT NULL COMMENT '附件id',
  `pic_path` varchar(155) DEFAULT NULL COMMENT '图片路径',
  `pic_name` varchar(50) DEFAULT NULL COMMENT '图片名',
  PRIMARY KEY (`attachment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片附件表，存储图片的存储信息';

-- ----------------------------
-- Records of tb_attachment
-- ----------------------------
INSERT INTO `tb_attachment` VALUES ('0192d1cde3474a13836a9ba6b9610bb2', 'F:/eclipse/test/photos/1/', '1513671853905.jpg');
INSERT INTO `tb_attachment` VALUES ('1', 'F:\\test\\', 'fj001001.png');
INSERT INTO `tb_attachment` VALUES ('2', 'F:\\test\\', 'fj001002.png');
INSERT INTO `tb_attachment` VALUES ('3', 'F:\\test\\', 'fj001003.png');
INSERT INTO `tb_attachment` VALUES ('4', 'F:\\test\\', 'fj001004.png');
INSERT INTO `tb_attachment` VALUES ('43cf584850a247c7a89a888d41363d51', 'F:/eclipse/test/photos/1/', '1513668901647.jpg');
INSERT INTO `tb_attachment` VALUES ('5', 'F:\\test\\', 'fj001005.png');
INSERT INTO `tb_attachment` VALUES ('51cdf86cd15f478bae47e8b395051d2d', 'F:/eclipse/test/photos/1/', '1513668400534.jpg');
INSERT INTO `tb_attachment` VALUES ('53deff00d92942ba98caa97a163e6a67', 'F:/eclipse/test/photos/1/', '1513671657656.jpg');
INSERT INTO `tb_attachment` VALUES ('54719905f95141f4865c41f11d8bf5eb', 'F:/eclipse/test/photos/1/', '1513671902703.jpg');
INSERT INTO `tb_attachment` VALUES ('67fb0ce3af774c3485c35525eed8f0f0', 'F:/eclipse/test/photos/1/', '1513671536728.jpg');
INSERT INTO `tb_attachment` VALUES ('808b91ab84e54b96a0f17b65435c9d5e', 'F:/eclipse/test/photos/1/', '1514612129728.jpg');
INSERT INTO `tb_attachment` VALUES ('8592b15b447949d2bd4038ae526d1fe2', 'F:/eclipse/test/photos/1/', '1513666318348.jpg');
INSERT INTO `tb_attachment` VALUES ('b0bd74e117334319a8608b68853ab931', 'F:/eclipse/test/photos/1//', '1513653491672.jpg');
INSERT INTO `tb_attachment` VALUES ('cd473a16669547e2852b609da12bccdc', 'F:/eclipse/test/photos/1/', '1513669218451.jpg');
INSERT INTO `tb_attachment` VALUES ('e47627d1820f43f4b99058458a547e1c', 'F:/eclipse/test/photos/1/', '1513655040347.jpg');
INSERT INTO `tb_attachment` VALUES ('e87a588a325247949c982bdb794879af', 'F:/eclipse/test/photos/1/', '1513672457808.jpg');
INSERT INTO `tb_attachment` VALUES ('f05093dc2ceb4a1ca13bb2fb0b59d9e2', 'F:/eclipse/test/photos/1/', '1514611738245.jpg');
