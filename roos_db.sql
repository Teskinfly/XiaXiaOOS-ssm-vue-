/*
 Navicat Premium Data Transfer

 Source Server         : con1
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : roos_db

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 24/12/2020 16:08:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `a_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `a_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c_des` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_price` int(11) NULL DEFAULT NULL,
  `f_img` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_c_id` int(11) NULL DEFAULT NULL,
  `f_des` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `f_inventory` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`f_id`) USING BTREE,
  INDEX `f_c_id`(`f_c_id`) USING BTREE,
  CONSTRAINT `f_c_id` FOREIGN KEY (`f_c_id`) REFERENCES `category` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `m_u_id` int(11) NOT NULL,
  `m_content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_f_id` int(11) NOT NULL,
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`m_id`) USING BTREE,
  INDEX `m_f_id`(`m_f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail`  (
  `od_id` int(11) NOT NULL AUTO_INCREMENT,
  `od_o_id` int(11) NULL DEFAULT NULL,
  `od_f_id` int(11) NULL DEFAULT NULL,
  `od_f_amount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `union`(`od_id`, `od_o_id`, `od_f_id`, `od_f_amount`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `o_id` int(11) NOT NULL AUTO_INCREMENT,
  `o_date` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `o_payment` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `o_u_id` int(11) NULL DEFAULT NULL,
  `o_price` decimal(10, 2) NULL DEFAULT NULL,
  `o_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `o_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`o_id`) USING BTREE,
  INDEX `tt`(`o_u_id`, `o_date`, `o_price`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1179691 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `c1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c4` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `c5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tt`(`c1`, `c2`, `c3`, `c4`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_pn` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_money` bigint(255) NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 121223130 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
