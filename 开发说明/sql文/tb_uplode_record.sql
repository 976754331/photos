/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:5306
Source Database       : photos

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-26 09:08:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_uplode_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_uplode_record`;
CREATE TABLE `tb_uplode_record` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `pic_name` varchar(255) DEFAULT NULL COMMENT '图片路径加名称',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `uplode_date` varchar(50) DEFAULT NULL COMMENT '上传时间',
  `type_id` varchar(50) DEFAULT NULL COMMENT '类型id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传图片记录表';

-- ----------------------------
-- Records of tb_uplode_record
-- ----------------------------
INSERT INTO `tb_uplode_record` VALUES ('1', '1', '1', '1', null);
INSERT INTO `tb_uplode_record` VALUES ('bb0458101e4142f0897f4816cce561d1', 'file:///storage/emulated/0/Android/data/com.hoperun.cma/cache/S71208-172643.jpg', '1', '2017-12-19 14:51:58', null);
INSERT INTO `tb_uplode_record` VALUES ('84d71bfe4cd9400e986670b303ba64b8', 'file:///storage/emulated/0/Android/data/com.hoperun.cma/cache/S71208-172628.jpg', '1', '2017-12-19 15:26:45', null);
INSERT INTO `tb_uplode_record` VALUES ('517607600556455e8c90c17a005fa6e1', 'file:///storage/emulated/0/Android/data/com.hoperun.cma/cache/S71205-125523.jpg', '1', '2017-12-19 15:35:01', null);
INSERT INTO `tb_uplode_record` VALUES ('6fd4d81983104ba1832b2e786c5e8962', 'file:///storage/emulated/0/Android/data/com.hoperun.cma/cache/1510299662291.jpg', '1', '2017-12-19 15:40:18', null);
INSERT INTO `tb_uplode_record` VALUES ('4277bc4106a64afba57d03c3bd405506', 'file:///storage/emulated/0/Android/data/com.hoperun.cma/cache/S71205-125518.jpg', '1', '2017-12-19 16:18:56', null);
INSERT INTO `tb_uplode_record` VALUES ('16fb856f94f64c7e93de141cc5fff226', 'file:///storage/emulated/0/Android/data/com.hoperun.cma/cache/S71207-204610.jpg', '1', '2017-12-19 16:20:57', null);
INSERT INTO `tb_uplode_record` VALUES ('9ce6060efbc6419e9c503870984e41c2', '/storage/emulated/0/Pictures/Screenshots/S71208-172643.jpg', '1', '2017-12-19 16:24:13', null);
INSERT INTO `tb_uplode_record` VALUES ('883912103c174dac8e442693812c4bfd', '/storage/emulated/0/DCIM/P71105-152934.jpg', '1', '2017-12-19 16:25:02', null);
INSERT INTO `tb_uplode_record` VALUES ('f6df1e372256456f9267137ca07a9e8d', '/storage/emulated/0/DCIM/P71105-152928.jpg', '1', '2017-12-19 16:34:17', null);
INSERT INTO `tb_uplode_record` VALUES ('bd9ea7dcf0474044b556a0fa167a0c79', 'file:///storage/emulated/0/Android/data/com.hoperun.cma/cache/1514611735651.jpg', '1', '2017-12-30 13:28:58', '206');
INSERT INTO `tb_uplode_record` VALUES ('11ab03ad54ea439897558c9ef98d6445', '/storage/emulated/0/DCIM/P71005-184444.jpg', '1', '2017-12-30 13:35:29', '303');
