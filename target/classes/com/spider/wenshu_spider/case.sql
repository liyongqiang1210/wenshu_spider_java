/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : lyq_db

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-10-05 07:14:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for case
-- ----------------------------
DROP TABLE IF EXISTS `case`;
CREATE TABLE `case` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `case_name` varchar(200) NOT NULL COMMENT '案件名',
  `case_type` int(1) NOT NULL COMMENT '案件类型（1.刑事案件2.民事案件，3.行政案件，4.赔偿案件，5.执行案件）',
  `case_key_word` varchar(200) NOT NULL COMMENT '案件关键字',
  `court_id` int(5) NOT NULL,
  `case_date` varchar(50) DEFAULT NULL COMMENT '裁判年份',
  `case_doc_type` varchar(50) DEFAULT NULL COMMENT '裁判文书类型',
  `case_trial_program` varchar(50) DEFAULT NULL COMMENT '审判程序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of case
-- ----------------------------
