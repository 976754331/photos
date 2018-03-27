/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:5306
Source Database       : photos

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-26 09:07:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_types
-- ----------------------------
DROP TABLE IF EXISTS `tb_types`;
CREATE TABLE `tb_types` (
  `pic_id` varchar(50) DEFAULT NULL COMMENT '图片id',
  `type_id` varchar(50) DEFAULT NULL COMMENT '分类id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片分类表，实现图片与类型多对多';

-- ----------------------------
-- Records of tb_types
-- ----------------------------
INSERT INTO `tb_types` VALUES ('1', '201');
INSERT INTO `tb_types` VALUES ('2', '201');
INSERT INTO `tb_types` VALUES ('3', '201');
INSERT INTO `tb_types` VALUES ('4', '201');
INSERT INTO `tb_types` VALUES ('5', '201');
INSERT INTO `tb_types` VALUES ('7087d29378f34a099b236e3131765356', 'fc47a01d7b844ce1996f338ce2410f38');
INSERT INTO `tb_types` VALUES ('38f99d328f7744aa9661874d20c423ce', 'fc47a01d7b844ce1996f338ce2410f38');
INSERT INTO `tb_types` VALUES ('eb9b78a80a754412af4b9f62712569ea', 'e7565fd0e67f48678668e6a1cc4e4c8f');
INSERT INTO `tb_types` VALUES ('0f24636c8af04d4d835d4219ab9102d6', 'e7565fd0e67f48678668e6a1cc4e4c8f');
INSERT INTO `tb_types` VALUES ('98be910c9c5d40f79ba2b5a32fb41805', 'e7565fd0e67f48678668e6a1cc4e4c8f');
INSERT INTO `tb_types` VALUES ('b2daf74e81d54bb09a6a6d56a5ae33ce', 'e7565fd0e67f48678668e6a1cc4e4c8f');
INSERT INTO `tb_types` VALUES ('e683f09019754aaf9f40cc10e7adbe33', 'e7565fd0e67f48678668e6a1cc4e4c8f');
INSERT INTO `tb_types` VALUES ('645eef12cd1b49949bf59f81abf5f308', 'e7565fd0e67f48678668e6a1cc4e4c8f');
INSERT INTO `tb_types` VALUES ('4ec1c72971e74380b4b22bb80fe51595', 'e7565fd0e67f48678668e6a1cc4e4c8f');
INSERT INTO `tb_types` VALUES ('ab0c0c15f5df4c318a1babc76ab2848d', 'e7565fd0e67f48678668e6a1cc4e4c8f');
INSERT INTO `tb_types` VALUES ('25176017bf5547b7b818862f614ea1e9', 'e7565fd0e67f48678668e6a1cc4e4c8f');
INSERT INTO `tb_types` VALUES ('0a7d7055fd7e4f448990632d7df69c3a', 'e7565fd0e67f48678668e6a1cc4e4c8f');
INSERT INTO `tb_types` VALUES ('2ccd5ef5622f46d8a535e18880630ae1', 'e7565fd0e67f48678668e6a1cc4e4c8f');
INSERT INTO `tb_types` VALUES ('9bde17e2e940403abb9dadb254002b56', 'f538d95586924ff58581ba90649001c4');
INSERT INTO `tb_types` VALUES ('6878a561cf4e4f958e4e911acea4e3c8', 'f538d95586924ff58581ba90649001c4');
INSERT INTO `tb_types` VALUES ('c51a851927cd4e379bffcb28f2f0eec6', 'f538d95586924ff58581ba90649001c4');
INSERT INTO `tb_types` VALUES ('5c67c5bf86af4ceb9c7186ea31e3d3ae', 'f538d95586924ff58581ba90649001c4');
INSERT INTO `tb_types` VALUES ('0cf5e85a3d11413db645e3b001161c09', '7194f63a341b4634a316062f4d5aceca');
INSERT INTO `tb_types` VALUES ('5c8d13ca8e1b43d189c93ab04946b3e5', '3d11bedb300341658d20752dacdecb27');
INSERT INTO `tb_types` VALUES ('bb37f00884b24d93afc138561826f9ae', '3d11bedb300341658d20752dacdecb27');
INSERT INTO `tb_types` VALUES ('702e505578db47ac80b6cb1ec259d8d5', '3d11bedb300341658d20752dacdecb27');
INSERT INTO `tb_types` VALUES ('4f9aa5affd76483a9888179548f1d27f', 'dd684562f2cd4b05aa989ace9a6852d0');
INSERT INTO `tb_types` VALUES ('f129683791d44c52bcc19484e86db48c', 'dd684562f2cd4b05aa989ace9a6852d0');
INSERT INTO `tb_types` VALUES ('8ae258189fb04452b552e9d0da9631a8', 'dd684562f2cd4b05aa989ace9a6852d0');
INSERT INTO `tb_types` VALUES ('5e5620d23c8a478f826924f0c329a250', 'dd684562f2cd4b05aa989ace9a6852d0');
INSERT INTO `tb_types` VALUES ('adca91eb5f8b4db7a70abfc7b46eb84c', 'dd684562f2cd4b05aa989ace9a6852d0');
INSERT INTO `tb_types` VALUES ('0b27fb97ce0f468a98304e476b3b7d90', 'dd684562f2cd4b05aa989ace9a6852d0');
INSERT INTO `tb_types` VALUES ('5ff911c24f33451894a04a73a99b0c42', 'dd684562f2cd4b05aa989ace9a6852d0');
INSERT INTO `tb_types` VALUES ('cdd675fdb28241a0805bee062ffbe67f', 'dd684562f2cd4b05aa989ace9a6852d0');
INSERT INTO `tb_types` VALUES ('4f6334b096ff4bccba81348c14666bd3', 'dd684562f2cd4b05aa989ace9a6852d0');
INSERT INTO `tb_types` VALUES ('0227016dd071429bb3097bd83de1bc5f', 'dd684562f2cd4b05aa989ace9a6852d0');
INSERT INTO `tb_types` VALUES ('fa5aef38ccc34532a2869d634d2fb416', 'dd684562f2cd4b05aa989ace9a6852d0');
INSERT INTO `tb_types` VALUES ('23739d7a0c724da4869ff93d57c6558e', '8f0eb49a30c04c55af289c9485f0e95c');
INSERT INTO `tb_types` VALUES ('2bbb2975e13d40068df939ba00a2f667', '8f0eb49a30c04c55af289c9485f0e95c');
INSERT INTO `tb_types` VALUES ('b944aed0dcb04f6394580e01d948c65b', '8f0eb49a30c04c55af289c9485f0e95c');
INSERT INTO `tb_types` VALUES ('69994ed993f446a7b6d998075c176cf1', '8f0eb49a30c04c55af289c9485f0e95c');
INSERT INTO `tb_types` VALUES ('1ded5430e7554a799cb55304f5566819', '9f72226f29a548d09c421fca55835c05');
INSERT INTO `tb_types` VALUES ('c2853e6a907447c7b83b61219d4f2671', '9f72226f29a548d09c421fca55835c05');
INSERT INTO `tb_types` VALUES ('c1ec8e6eb46b42e2bab9d2dc53c9f5e2', '9f72226f29a548d09c421fca55835c05');
INSERT INTO `tb_types` VALUES ('eca89a1f62264b76af6a93d0466122b4', '9ba5b641c6964ace96eca4ab86fcf0ad');
INSERT INTO `tb_types` VALUES ('5499cb0bf262410cbe254b4ecb37fe52', '9ba5b641c6964ace96eca4ab86fcf0ad');
INSERT INTO `tb_types` VALUES ('bb48261174014726b0af922d4c62cd26', '9ba5b641c6964ace96eca4ab86fcf0ad');
INSERT INTO `tb_types` VALUES ('25b6eb8f805242ecb903b033ba71b64c', '781a9c17fee9485b98e9069423b2b76b');
INSERT INTO `tb_types` VALUES ('ce96d341cb1c4c88bac46159a3917427', '781a9c17fee9485b98e9069423b2b76b');
INSERT INTO `tb_types` VALUES ('7798f0389e794b5092e51eb4e92fbf37', '781a9c17fee9485b98e9069423b2b76b');
INSERT INTO `tb_types` VALUES ('cc2e0531bc00486b91d1d9af9ac450aa', '781a9c17fee9485b98e9069423b2b76b');
INSERT INTO `tb_types` VALUES ('21730c1bce994d8b9dd3e233cc1dc621', '781a9c17fee9485b98e9069423b2b76b');
INSERT INTO `tb_types` VALUES ('6ddbfb1c85444df589ad3e90bf6245a9', '781a9c17fee9485b98e9069423b2b76b');
INSERT INTO `tb_types` VALUES ('addb6ae8bdd3447593b55a8073d6b2b7', '781a9c17fee9485b98e9069423b2b76b');
INSERT INTO `tb_types` VALUES ('4504bbd6aa7f4876b47b8c86938b2e3a', '781a9c17fee9485b98e9069423b2b76b');
INSERT INTO `tb_types` VALUES ('e02bea304fcc481ba3d40bf3ddb192f2', '781a9c17fee9485b98e9069423b2b76b');
INSERT INTO `tb_types` VALUES ('38ae416315874af0a7d860b2f2bb3a67', '781a9c17fee9485b98e9069423b2b76b');
INSERT INTO `tb_types` VALUES ('ea6633e7fbf84a11b099d2b8436c51e9', '781a9c17fee9485b98e9069423b2b76b');
INSERT INTO `tb_types` VALUES ('6ee829d4080845c19afdb8891f31fed7', 'e09ac620f5794a04a7c9a3f6a36f53b6');
INSERT INTO `tb_types` VALUES ('c21a2a954cc04a6ab57aee5328e9e9aa', 'e09ac620f5794a04a7c9a3f6a36f53b6');
INSERT INTO `tb_types` VALUES ('d373c07ed2134996b3e69ad5d61ddaaa', 'e09ac620f5794a04a7c9a3f6a36f53b6');
INSERT INTO `tb_types` VALUES ('a433586752504a869610356b10bb81aa', 'e09ac620f5794a04a7c9a3f6a36f53b6');
INSERT INTO `tb_types` VALUES ('a3cfd86efd7d4769b10958ae77cc3b75', 'e6762197603d4110bd0b037dc2980d9b');
INSERT INTO `tb_types` VALUES ('d78a1bbf689f4d9aae1d699c0c653999', 'e6762197603d4110bd0b037dc2980d9b');
INSERT INTO `tb_types` VALUES ('c0c99d423b2347c6aebfa4560594fa71', 'e6762197603d4110bd0b037dc2980d9b');
INSERT INTO `tb_types` VALUES ('a503d1d5e83d4831ae74addc69707230', 'ff554510eb684bbab0990c5d2d209a70');
INSERT INTO `tb_types` VALUES ('2c0e3feefbb4496eb55ab2791508d4d3', 'ff554510eb684bbab0990c5d2d209a70');
INSERT INTO `tb_types` VALUES ('632f7fcaf3e44888aa17400cccdb655a', 'ff554510eb684bbab0990c5d2d209a70');
INSERT INTO `tb_types` VALUES ('30e5d83bfcae4bf198232b43ddaa28b0', '73f73a8129bb4403ac6cffc7dcb24257');
INSERT INTO `tb_types` VALUES ('3c31d1eeaedd4129b24b0acd313b295e', '73f73a8129bb4403ac6cffc7dcb24257');
INSERT INTO `tb_types` VALUES ('a4defbaad5b94d3d9ff87d46b58307a2', '73f73a8129bb4403ac6cffc7dcb24257');
INSERT INTO `tb_types` VALUES ('fc95ae146e3943538ee53dac273364c1', '73f73a8129bb4403ac6cffc7dcb24257');
INSERT INTO `tb_types` VALUES ('c6e853b1eae5434dab1267d47e401132', '73f73a8129bb4403ac6cffc7dcb24257');
INSERT INTO `tb_types` VALUES ('6e2981d7c6984924a1daa3c875025186', '73f73a8129bb4403ac6cffc7dcb24257');
INSERT INTO `tb_types` VALUES ('69ede36c90924ca7a4537e51bc55c672', '73f73a8129bb4403ac6cffc7dcb24257');
INSERT INTO `tb_types` VALUES ('85d90ace8d364b7faeb179f99425db99', '73f73a8129bb4403ac6cffc7dcb24257');
INSERT INTO `tb_types` VALUES ('69c4f209da8642c9a6110679a4fde645', '73f73a8129bb4403ac6cffc7dcb24257');
INSERT INTO `tb_types` VALUES ('8dc048898e534de4afade437dadb8e3b', '73f73a8129bb4403ac6cffc7dcb24257');
INSERT INTO `tb_types` VALUES ('4e624807f7d94ad79e311c41e6f56926', '73f73a8129bb4403ac6cffc7dcb24257');
INSERT INTO `tb_types` VALUES ('ff3457ccdb0b4e8b8f7c20e0426d78f5', '5bac54da62aa48188a0844502749f64d');
INSERT INTO `tb_types` VALUES ('65dbf64d81834dfbb4552cb85ccb4b14', '5bac54da62aa48188a0844502749f64d');
INSERT INTO `tb_types` VALUES ('27ba68e6876c48eeaf462e308a1f11fc', '5bac54da62aa48188a0844502749f64d');
INSERT INTO `tb_types` VALUES ('7ef5f00f33a54f92a990d21ed4825745', '5bac54da62aa48188a0844502749f64d');
INSERT INTO `tb_types` VALUES ('951b54ccff2b42f9bfc1daa226c8c08d', 'a8a4bf468d914aac8098431aff02798f');
INSERT INTO `tb_types` VALUES ('54c4220b98ff4378b62fddbfee6fb949', 'a8a4bf468d914aac8098431aff02798f');
INSERT INTO `tb_types` VALUES ('9e42ec7bf4ea4874aea528fd5fc36946', 'a8a4bf468d914aac8098431aff02798f');
INSERT INTO `tb_types` VALUES ('c25c96e54f964950b30c906688a0eaa4', 'e121173d380444d395cab5d364974739');
INSERT INTO `tb_types` VALUES ('7a459ea4dde944a7a2f8a23da8bad333', 'e121173d380444d395cab5d364974739');
INSERT INTO `tb_types` VALUES ('924128f34c0e42b19baf33863cabb7fd', 'e121173d380444d395cab5d364974739');
INSERT INTO `tb_types` VALUES ('93f96de64fe24ea0b294e1992acbde11', '22165dbe018443e1b03202dc60538906');
INSERT INTO `tb_types` VALUES ('8faf13044ae44a3da7b44245cead152f', '22165dbe018443e1b03202dc60538906');
INSERT INTO `tb_types` VALUES ('44393655bcd245bc8961071fa4aa6f42', '22165dbe018443e1b03202dc60538906');
INSERT INTO `tb_types` VALUES ('cffbf8624bd948889bce9c9b1867905d', '22165dbe018443e1b03202dc60538906');
INSERT INTO `tb_types` VALUES ('ee719302b4074cd3a8fae1a4d8c72104', '22165dbe018443e1b03202dc60538906');
INSERT INTO `tb_types` VALUES ('12900f06c23b4302b3b04bfb87c3564f', '22165dbe018443e1b03202dc60538906');
INSERT INTO `tb_types` VALUES ('38dac7128887436ea45ba24119a0e77a', '22165dbe018443e1b03202dc60538906');
INSERT INTO `tb_types` VALUES ('ce14e560f1a94e8fa6aab06998719e78', '22165dbe018443e1b03202dc60538906');
INSERT INTO `tb_types` VALUES ('38238770ba7f42798dc487d6da844e14', '22165dbe018443e1b03202dc60538906');
INSERT INTO `tb_types` VALUES ('4fd880c45196497bb8804fd6bda3fc6b', '22165dbe018443e1b03202dc60538906');
INSERT INTO `tb_types` VALUES ('e2a614ba22784b7e85faff7e4c2b4ea1', '22165dbe018443e1b03202dc60538906');
INSERT INTO `tb_types` VALUES ('c990e540233c412aa6022a7e9c933b49', 'f0514266140542fab7f45b0f832a78c4');
INSERT INTO `tb_types` VALUES ('a9c57bbda8d0490890105bd608fbb3f1', 'f0514266140542fab7f45b0f832a78c4');
INSERT INTO `tb_types` VALUES ('b935be0a49f94ccb9adf59d9d679186f', 'f0514266140542fab7f45b0f832a78c4');
INSERT INTO `tb_types` VALUES ('10a10d1dcb704c55837ce59c6f46d7d6', 'f0514266140542fab7f45b0f832a78c4');
INSERT INTO `tb_types` VALUES ('ba8b7fb273eb4730ad11f3abe9c38cef', '4395a8705f494e728503090146d6e656');
INSERT INTO `tb_types` VALUES ('48616e449a6a46a4a5f5c8bcf82442e0', '4395a8705f494e728503090146d6e656');
INSERT INTO `tb_types` VALUES ('629d9d98bc184f4d87300f28075d5ebc', '4395a8705f494e728503090146d6e656');
INSERT INTO `tb_types` VALUES ('c088cb9d8aad4f4b98687581a2ed1780', '218c58810ee2470c9faf4db64d4023d0');
INSERT INTO `tb_types` VALUES ('283bdc3a9eec42dbab4b694e2cf6ad09', '218c58810ee2470c9faf4db64d4023d0');
INSERT INTO `tb_types` VALUES ('ef37b3b2b73a499aa8bc882b6b8e86ef', '218c58810ee2470c9faf4db64d4023d0');
INSERT INTO `tb_types` VALUES ('11af6a1042554eeaafcc635db7f39017', null);
INSERT INTO `tb_types` VALUES ('a02a703966724a38a76fc5f94177f149', null);
INSERT INTO `tb_types` VALUES ('4dc30744c51e41398e96f3bdb12f8341', '203');
INSERT INTO `tb_types` VALUES ('be4cedc4318f406eb3f6f059fae8fb44', '204');
INSERT INTO `tb_types` VALUES ('790645b7835a4d4c8c549bae8639a6c4', '203');
INSERT INTO `tb_types` VALUES ('58c9debc84024843a4f9b4188cdf1f16', '206');
INSERT INTO `tb_types` VALUES ('0e6ca1e8d97d45e0aaadf6070302c601', '302');
INSERT INTO `tb_types` VALUES ('eb72e78143934eb3959a968d0dac6034', '303');
INSERT INTO `tb_types` VALUES ('e20e87c01b6c411591599669dc306a1c', '303');
INSERT INTO `tb_types` VALUES ('cd518b8f63e64367a1958cd3a3f31112', '303');
INSERT INTO `tb_types` VALUES ('da9df7afe3e743d388782696ad2e0854', '303');
INSERT INTO `tb_types` VALUES ('2dadf81c29fc4ba2811f0fa13367ccac', '206');
INSERT INTO `tb_types` VALUES ('af76d3c465f44bb2be848f815f367a22', '303');
