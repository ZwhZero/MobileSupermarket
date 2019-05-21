/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : market

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-05-19 19:15:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `aId` int(11) NOT NULL AUTO_INCREMENT,
  `aName` varchar(50) NOT NULL,
  `aPassword` varchar(50) NOT NULL,
  PRIMARY KEY (`aId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'test', '123456');
INSERT INTO `t_admin` VALUES ('2', 'zero', '123');
INSERT INTO `t_admin` VALUES ('3', 'John10', '1234');
INSERT INTO `t_admin` VALUES ('4', 'William', 'william');
INSERT INTO `t_admin` VALUES ('5', 'zyp', '123456');

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `cId` int(11) NOT NULL AUTO_INCREMENT,
  `cName` varchar(50) NOT NULL,
  `level` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`cId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------
INSERT INTO `t_category` VALUES ('1', '休闲零食', '1', '0');
INSERT INTO `t_category` VALUES ('2', '酒水乳饮', '1', '0');
INSERT INTO `t_category` VALUES ('3', '粮油副食', '1', '0');
INSERT INTO `t_category` VALUES ('4', '生活用品', '1', '0');
INSERT INTO `t_category` VALUES ('5', '糕点饼干', '2', '1');
INSERT INTO `t_category` VALUES ('6', '糖果类', '2', '1');
INSERT INTO `t_category` VALUES ('7', '坚果干果', '2', '1');
INSERT INTO `t_category` VALUES ('8', '乳制品', '2', '2');
INSERT INTO `t_category` VALUES ('9', '果汁饮料', '2', '2');
INSERT INTO `t_category` VALUES ('10', '酒类', '2', '2');
INSERT INTO `t_category` VALUES ('11', '五谷杂粮', '2', '3');
INSERT INTO `t_category` VALUES ('12', '方便速食', '2', '3');
INSERT INTO `t_category` VALUES ('13', '厨房调味', '2', '3');
INSERT INTO `t_category` VALUES ('14', '清洁工具', '2', '4');
INSERT INTO `t_category` VALUES ('15', '水杯水壶', '2', '4');
INSERT INTO `t_category` VALUES ('16', '个人护理', '2', '4');
INSERT INTO `t_category` VALUES ('18', 'one', '2', '1');
INSERT INTO `t_category` VALUES ('20', 'two', '2', '1');

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `gId` int(11) NOT NULL AUTO_INCREMENT,
  `gName` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `sellprice` decimal(8,2) NOT NULL,
  `sellcount` int(11) NOT NULL DEFAULT '0',
  `count` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  `imgurl` varchar(255) NOT NULL,
  PRIMARY KEY (`gId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('1', '港荣蒸蛋糕', '好吃不上火', '39.50', '0', '100', '5', 'grzdg.png');
INSERT INTO `t_goods` VALUES ('2', '奥利奥饼干', '独立装12小包349g', '25.90', '2', '98', '5', 'ala.png');
INSERT INTO `t_goods` VALUES ('3', '大白兔奶糖', '227g/包 原味奶糖 奶香浓郁', '15.00', '2', '98', '6', 'dbtnt.png');
INSERT INTO `t_goods` VALUES ('4', '不二家棒棒糖', '多种口味 果味 20支 125g/袋', '19.90', '0', '100', '6', 'bej.png');
INSERT INTO `t_goods` VALUES ('5', '三只松鼠开心果', '原味 225g ', '59.50', '0', '100', '7', 'kxg.png');
INSERT INTO `t_goods` VALUES ('6', '百草味葡萄干', '无核 白葡萄干 200g', '15.90', '0', '100', '7', 'ptg.png');
INSERT INTO `t_goods` VALUES ('7', '安佳纯牛奶', '24盒 进口牛奶整箱销售', '99.00', '0', '100', '8', 'ajcnn.png');
INSERT INTO `t_goods` VALUES ('8', '安慕希酸奶', '16盒 整箱优惠出售', '66.00', '0', '100', '8', 'amxsn.png');
INSERT INTO `t_goods` VALUES ('9', '美汁源果粒橙', '橙汁饮料 1.25L', '7.90', '1', '99', '9', 'glc.png');
INSERT INTO `t_goods` VALUES ('10', '百事可乐', '整箱销售 更优惠', '45.90', '1', '99', '9', 'bskl.png');
INSERT INTO `t_goods` VALUES ('11', '雪花啤酒', '整箱 330ml*24', '54.50', '0', '100', '10', 'xhpj.png');
INSERT INTO `t_goods` VALUES ('12', '广式菠萝啤', '果味啤酒 330ml*24罐', '49.90', '0', '100', '10', 'gsblp.png');
INSERT INTO `t_goods` VALUES ('13', '福临门大米', '苏北硬米 10kg', '55.80', '1', '99', '11', 'flmdm.png');
INSERT INTO `t_goods` VALUES ('14', '金龙鱼大米', '东北大米 10kg', '59.90', '1', '99', '11', 'jlydm.png');
INSERT INTO `t_goods` VALUES ('15', '辛拉面', '韩国进口 120g*5', '27.90', '4', '96', '12', 'xlm.png');
INSERT INTO `t_goods` VALUES ('16', '金锣肉粒多', '金锣火腿肠 40g*8', '13.80', '4', '96', '12', 'jlrld.png');
INSERT INTO `t_goods` VALUES ('17', '海天酱油', '海天味极鲜特级酱油1280ml', '19.90', '0', '100', '13', 'htjy.png');
INSERT INTO `t_goods` VALUES ('18', '海天黄豆酱', '海天黄豆酱800g', '15.90', '0', '100', '13', 'hthdj.png');
INSERT INTO `t_goods` VALUES ('19', '美丽雅扫把组合装', '美丽雅扫把+扫帚 塑料组合装', '29.90', '0', '100', '14', 'mlysb.png');
INSERT INTO `t_goods` VALUES ('20', '思高百洁布', '3M思高百洁布厨房家用 5pic', '10.60', '2', '98', '14', 'sgbjb.png');
INSERT INTO `t_goods` VALUES ('21', '富光玻璃杯', '富光双层男女学生水杯便携泡茶杯过滤杯子', '29.90', '1', '99', '15', 'fgblb.png');
INSERT INTO `t_goods` VALUES ('22', '贝瑟斯马克杯', '贝瑟斯带盖陶瓷杯子马克杯咖啡杯 带勺', '13.90', '0', '100', '15', 'bssmkb.png');
INSERT INTO `t_goods` VALUES ('23', '清扬洗发露', '清扬 男士去屑洗发露 750g', '53.90', '2', '998', '16', 'qyxfl.png');
INSERT INTO `t_goods` VALUES ('24', '力士沐浴露', '力士/LUX精油香氛沐浴露/乳 幽莲魅肤 1kg', '29.90', '3', '97', '16', 'lsmyl.png');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `uId` int(11) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `oNumber` int(11) NOT NULL,
  `oMoney` decimal(8,2) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `remark` varchar(255) DEFAULT NULL,
  `orderState` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '2', '15622161515', '海校1048', '5', '145.50', '2019-04-08 15:45:28', '我看行gg', '2');
INSERT INTO `t_order` VALUES ('2', '2', '11325865811', '广州航海学院1048', '6', '105.10', '2019-04-09 20:06:53', '第二次购买', '2');
INSERT INTO `t_order` VALUES ('3', '2', '11325865811', '广州黄埔', '3', '43.80', '2019-05-01 15:24:20', '赶紧发货，谢谢', '2');
INSERT INTO `t_order` VALUES ('4', '2', '11325865811', '海校北4-1048', '3', '97.70', '2019-05-01 21:45:51', '防破损', '1');
INSERT INTO `t_order` VALUES ('5', '7', '12332145678', '哈尔滨', '4', '106.90', '2019-05-17 15:30:11', '尽快发货拉拉', '1');
INSERT INTO `t_order` VALUES ('7', '2', '11325865811', '广州黄埔', '1', '59.90', '2019-05-17 15:53:12', 'tttt', '1');
INSERT INTO `t_order` VALUES ('10', '2', '11325865811', '广州黄埔', '2', '107.80', '2019-05-17 16:47:54', 'sdsdsd', '1');

-- ----------------------------
-- Table structure for t_orderitem
-- ----------------------------
DROP TABLE IF EXISTS `t_orderitem`;
CREATE TABLE `t_orderitem` (
  `itemId` int(11) NOT NULL AUTO_INCREMENT,
  `uId` int(11) NOT NULL,
  `gId` int(11) NOT NULL,
  `gName` varchar(50) NOT NULL,
  `gPrice` decimal(8,2) NOT NULL,
  `gCount` int(11) NOT NULL,
  `orderId` int(11) NOT NULL,
  `addTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_orderitem
-- ----------------------------
INSERT INTO `t_orderitem` VALUES ('1', '2', '24', '力士沐浴露', '29.90', '3', '1', '2019-04-06 12:17:16');
INSERT INTO `t_orderitem` VALUES ('2', '2', '15', '辛拉面', '27.90', '2', '1', '2019-04-06 12:17:23');
INSERT INTO `t_orderitem` VALUES ('3', '2', '15', '辛拉面', '27.90', '2', '2', '2019-04-09 20:06:53');
INSERT INTO `t_orderitem` VALUES ('4', '2', '16', '金锣肉粒多', '13.80', '3', '2', '2019-04-09 20:06:53');
INSERT INTO `t_orderitem` VALUES ('5', '2', '9', '美汁源果粒橙', '7.90', '1', '2', '2019-04-09 20:06:53');
INSERT INTO `t_orderitem` VALUES ('6', '2', '3', '大白兔奶糖', '15.00', '2', '3', '2019-05-01 15:24:20');
INSERT INTO `t_orderitem` VALUES ('7', '2', '16', '金锣肉粒多', '13.80', '1', '3', '2019-05-01 15:24:20');
INSERT INTO `t_orderitem` VALUES ('8', '2', '2', '奥利奥饼干', '25.90', '2', '4', '2019-05-01 21:45:51');
INSERT INTO `t_orderitem` VALUES ('9', '2', '10', '百事可乐', '45.90', '1', '4', '2019-05-01 21:45:51');
INSERT INTO `t_orderitem` VALUES ('10', '7', '20', '思高百洁布', '10.60', '2', '5', '2019-05-17 15:30:11');
INSERT INTO `t_orderitem` VALUES ('11', '7', '13', '福临门大米', '55.80', '1', '5', '2019-05-17 15:30:11');
INSERT INTO `t_orderitem` VALUES ('12', '7', '21', '富光玻璃杯', '29.90', '1', '5', '2019-05-17 15:30:11');
INSERT INTO `t_orderitem` VALUES ('13', '2', '14', '金龙鱼大米', '59.90', '1', '7', '2019-05-17 15:53:12');
INSERT INTO `t_orderitem` VALUES ('14', '2', '23', '清扬洗发露', '53.90', '2', '10', '2019-05-17 16:47:54');

-- ----------------------------
-- Table structure for t_shoppingcart
-- ----------------------------
DROP TABLE IF EXISTS `t_shoppingcart`;
CREATE TABLE `t_shoppingcart` (
  `cartId` int(11) NOT NULL AUTO_INCREMENT,
  `uId` int(11) NOT NULL,
  `gId` int(11) NOT NULL,
  `gCount` int(11) NOT NULL DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cartId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shoppingcart
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `uId` int(11) NOT NULL AUTO_INCREMENT,
  `uName` varchar(50) NOT NULL,
  `age` varchar(10) DEFAULT NULL,
  `uPassword` varchar(50) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'test', '20', '123456', '男', '12345678910', '北区b51048', '123456@qq.com');
INSERT INTO `t_user` VALUES ('2', 'zero', '22', '123', '男', '11325865811', '广州黄埔', '534242@qq.com');
INSERT INTO `t_user` VALUES ('3', 'aaaaa', '22', 'aaaaa', '男', '11111111111', '广州', '11111@qq.com');
INSERT INTO `t_user` VALUES ('5', 'Jeffrey', '100', '12345678', '男', '18826132812', '广东饶平', 'Jeffrey@qq.com');
INSERT INTO `t_user` VALUES ('6', 'zyp', '22', '12345', '女', '15622161616', '广州黄埔', '5342420@qq.com');
INSERT INTO `t_user` VALUES ('7', 'Lihao', '15', '12345', '男', '12332145678', '哈尔滨', '188@qq.com');
