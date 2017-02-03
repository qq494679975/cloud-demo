/*
Navicat MySQL Data Transfer

Source Server         : 自己的库
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : oauth

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-02-03 09:47:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(255) DEFAULT NULL,
  KEY `token_id_index` (`token_id`),
  KEY `authentication_id_index` (`authentication_id`),
  KEY `user_name_index` (`user_name`),
  KEY `client_id_index` (`client_id`),
  KEY `refresh_token_index` (`refresh_token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` text,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `archived` tinyint(1) DEFAULT '0',
  `trusted` tinyint(1) DEFAULT '0',
  `autoapprove` varchar(255) DEFAULT 'false',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('mobile-client', 'mobile-resource', 'mobile', 'read,write', 'password,authorization_code,refresh_token,implicit,client_credentials', null, 'ROLE_CLIENT', null, null, null, '2017-01-17 17:49:17', '0', '0', 'false');
INSERT INTO `oauth_client_details` VALUES ('test_admin_client_id', 'unity-resource,mobile-resource', 'test_admin_client_secret', 'write', 'password,refresh_token', null, null, null, null, null, '2017-01-17 17:57:23', '0', '0', null);
INSERT INTO `oauth_client_details` VALUES ('unity-client', 'unity-resource', 'unity', 'read,write', 'password,authorization_code,refresh_token,implicit', null, 'ROLE_CLIENT', null, null, null, '2017-01-17 17:49:17', '0', '0', 'false');

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(255) DEFAULT NULL,
  `authentication` blob,
  KEY `code_index` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token_id` varchar(255) DEFAULT NULL,
  `token` blob,
  `authentication` blob,
  KEY `token_id_index` (`token_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for user_
-- ----------------------------
DROP TABLE IF EXISTS `user_`;
CREATE TABLE `user_` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `activated` tinyint(1) DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `default_user` tinyint(1) DEFAULT '0',
  `last_login_time` datetime DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `guid` (`guid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_
-- ----------------------------
INSERT INTO `user_` VALUES ('21', '29f6004fb1b0466f9572b02bf2ac1be8', '2017-01-17 17:49:17', '0', 'admin@wdcy.cc', '21232f297a57a5a743894a0e4a801fc3', '028-1234567', 'admin', '1', null, 'mobile-client');
INSERT INTO `user_` VALUES ('22', '55b713df1c6f423e842ad68668523c49', '2017-01-17 17:49:17', '0', 'unity@wdcy.cc', '439b3a25b555b3bc8667a09a036ae70c', '', 'unity', '0', null, 'mobile-client');
INSERT INTO `user_` VALUES ('23', '612025cb3f964a64a48bbdf77e53c2c1', '2017-01-17 17:49:17', '0', 'mobile@wdcy.cc', '532c28d5412dd75bf975fb951c740a30', '', 'mobile', '0', null, 'mobile-client');
INSERT INTO `user_` VALUES ('24', '210ffcdd808742d28dedb18b49296f6e', '2017-01-17 17:59:18', '0', '', '109633366fd0d46d371ede589998abaa', '', 'cwd', '0', null, 'mobile-client');

-- ----------------------------
-- Table structure for user_privilege
-- ----------------------------
DROP TABLE IF EXISTS `user_privilege`;
CREATE TABLE `user_privilege` (
  `user_id` int(11) DEFAULT NULL,
  `privilege` varchar(255) DEFAULT NULL,
  KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_privilege
-- ----------------------------
INSERT INTO `user_privilege` VALUES ('22', 'UNITY');
INSERT INTO `user_privilege` VALUES ('23', 'MOBILE');
INSERT INTO `user_privilege` VALUES ('24', 'MOBILE');
INSERT INTO `user_privilege` VALUES ('24', 'UNITY');
