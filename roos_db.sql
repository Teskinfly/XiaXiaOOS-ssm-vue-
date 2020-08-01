/*
 Navicat Premium Data Transfer

 Source Server         : con1
 Source Server Type    : MySQL
 Source Server Version : 50528
 Source Host           : localhost:3306
 Source Schema         : roos_db

 Target Server Type    : MySQL
 Target Server Version : 50528
 File Encoding         : 65001

 Date: 01/08/2020 11:40:52
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('lqk', '234', 1);

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
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '饮料', '好喝d');
INSERT INTO `category` VALUES (3, '饭类', '好吃');
INSERT INTO `category` VALUES (4, '汤类', '好香');
INSERT INTO `category` VALUES (6, '肉类', '好吃的肉');
INSERT INTO `category` VALUES (7, '鱼类', '');
INSERT INTO `category` VALUES (8, '素菜', '');
INSERT INTO `category` VALUES (10, '其他', '未被分类的其他种类');

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
  `f_amount` int(11) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE,
  INDEX `f_c_id`(`f_c_id`) USING BTREE,
  CONSTRAINT `f_c_id` FOREIGN KEY (`f_c_id`) REFERENCES `category` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES (38, '红烧茄子', 6, 'images/food/fishqie.jpg', 8, '好吃', 10);
INSERT INTO `food` VALUES (39, '梅菜扣肉', 12, 'images/food/food4.jpg', 6, '好吃', 70);
INSERT INTO `food` VALUES (40, '炒饭', 7, 'images/food/food3.jpg', 3, '好吃', 76);
INSERT INTO `food` VALUES (41, '可乐', 3, 'images/food/food1.jpg', 1, '好喝', 80);
INSERT INTO `food` VALUES (42, '水煮鱼', 20, 'images/food/waterFish.jpg', 7, '好吃', 100);
INSERT INTO `food` VALUES (43, '饺子', 6, 'images/food/jiaozi.jpg', 10, '好吃', 20);
INSERT INTO `food` VALUES (45, '鱼香肉丝', 15, 'images/food/AE077E0FA14E494C925A9D5410690C0B-123.jpg', 6, 'good', 1);
INSERT INTO `food` VALUES (48, '雪碧', 4, 'images/food/7D1F314940E548988DFC34488A51C068-xuebi.jpg', 1, '好喝', 3);
INSERT INTO `food` VALUES (51, '韩式酱汤', 15, 'images/food/3AC9182C4C554067914CE8D80F46B0CA-xiaxia.jpg', 4, '大虾、角瓜、土豆、元葱、冻豆腐、淘米水、韩式大酱、鸡精、味道。', 0);
INSERT INTO `food` VALUES (52, '可乐', 3, 'images/food/food1.jpg', 1, '好喝', 80);
INSERT INTO `food` VALUES (62, '可乐', 3, 'images/food/food1.jpg', 1, '好喝', 80);
INSERT INTO `food` VALUES (63, '雪碧', 4, 'images/food/7D1F314940E548988DFC34488A51C068-xuebi.jpg', 1, '好喝', 3);
INSERT INTO `food` VALUES (64, '雪碧', 4, 'images/food/7D1F314940E548988DFC34488A51C068-xuebi.jpg', 1, '好喝', 3);
INSERT INTO `food` VALUES (65, '雪碧', 4, 'images/food/7D1F314940E548988DFC34488A51C068-xuebi.jpg', 1, '好喝', 3);
INSERT INTO `food` VALUES (66, '雪碧', 4, 'images/food/7D1F314940E548988DFC34488A51C068-xuebi.jpg', 1, '好喝', 3);
INSERT INTO `food` VALUES (67, '雪碧', 4, 'images/food/7D1F314940E548988DFC34488A51C068-xuebi.jpg', 1, '好喝', 3);
INSERT INTO `food` VALUES (68, '雪碧', 4, 'images/food/7D1F314940E548988DFC34488A51C068-xuebi.jpg', 1, '好喝', 3);

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
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '老朱', '2019-12-01 11:01:49', 3, 21);
INSERT INTO `message` VALUES (1, '真香', '2019-12-08 22:09:51', 1, 22);
INSERT INTO `message` VALUES (7, '味道还行', '2019-12-16 09:40:19', 1, 23);
INSERT INTO `message` VALUES (1, '1楼', '2019-12-16 10:13:25', 2, 24);
INSERT INTO `message` VALUES (1, '承包空屏', '2019-12-16 10:14:05', 14, 25);

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
  `o_content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `o_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`o_id`) USING BTREE,
  INDEX `o_u_id`(`o_u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (78, '2020-08-01', '支付宝', 2, 26.00, '用户已接收', '#可乐-3#雪碧-4#炒饭-7#梅菜扣肉-12', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `orders` VALUES (79, '2020-08-01', '支付宝', 2, 47.00, '饭店接单中', '#韩式酱汤-15#梅菜扣肉-12#水煮鱼-20', '广东省梅州市嘉应学院江北校区南区***');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_pwd` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_pn` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'teskinfly', '12315', '13421193765', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `user` VALUES (2, 'lqk', '123', '131511512', '广东省梅州市嘉应学院江北校区南区***');
INSERT INTO `user` VALUES (10, '老王', '123', '1321532153', '广东省揭阳市*********');

SET FOREIGN_KEY_CHECKS = 1;
