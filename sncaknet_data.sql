/*
Navicat MySQL Data Transfer

Source Server         : yc
Source Server Version : 50555
Source Host           : localhost:3306
Source Database       : snacknet

Target Server Type    : MYSQL
Target Server Version : 50555
File Encoding         : 65001

Date: 2020-05-22 11:32:23
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `addrinfo`
-- ----------------------------
DROP TABLE IF EXISTS `addrinfo`;
CREATE TABLE `addrinfo` (
  `ano` varchar(100) COLLATE utf8_bin NOT NULL,
  `mno` int(11) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_bin NOT NULL,
  `tel` varchar(15) COLLATE utf8_bin NOT NULL,
  `province` varchar(100) COLLATE utf8_bin NOT NULL,
  `city` varchar(100) COLLATE utf8_bin NOT NULL,
  `area` varchar(100) COLLATE utf8_bin NOT NULL,
  `addr` varchar(100) COLLATE utf8_bin NOT NULL,
  `flag` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`ano`),
  KEY `FK_addrInfo_mno` (`mno`),
  CONSTRAINT `FK_addrInfo_mno` FOREIGN KEY (`mno`) REFERENCES `memberinfo` (`mno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of addrinfo
-- ----------------------------
INSERT INTO addrinfo VALUES ('1590052579423', '1', '周海军', '15096098010', '湖南省', '衡阳市', '珠晖区', '美的梧桐庄园', '0', '1');
INSERT INTO addrinfo VALUES ('1590053041766', '1', '源辰', '15096098010', '湖南省', '衡阳市', '珠晖区', '衡花路18号湖南工学院', '1', '1');
INSERT INTO addrinfo VALUES ('1590053187661', '1', '周天', '1509608011', '湖南省', '衡阳市', '珠晖区', '南华大学', '0', '1');

-- ----------------------------
-- Table structure for `admininfo`
-- ----------------------------
DROP TABLE IF EXISTS `admininfo`;
CREATE TABLE `admininfo` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(100) COLLATE utf8_bin NOT NULL,
  `pwd` varchar(100) COLLATE utf8_bin NOT NULL,
  `tel` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  UNIQUE KEY `aname` (`aname`),
  UNIQUE KEY `tel` (`tel`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of admininfo
-- ----------------------------
INSERT INTO admininfo VALUES ('101', 'navy', 'c8837b23ff8aaa8a2dde915473ce0991', '15096098088', '1');
INSERT INTO admininfo VALUES ('102', 'yc', '123321', '15096098011', null);
INSERT INTO admininfo VALUES ('103', '源辰', '123321', '15096098012', '1');

-- ----------------------------
-- Table structure for `cartinfo`
-- ----------------------------
DROP TABLE IF EXISTS `cartinfo`;
CREATE TABLE `cartinfo` (
  `cno` varchar(100) COLLATE utf8_bin NOT NULL,
  `mno` int(11) DEFAULT NULL,
  `gno` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`cno`),
  KEY `FK_cartInfo_mno` (`mno`),
  KEY `FK_cartInfo_gno` (`gno`),
  CONSTRAINT `FK_cartInfo_gno` FOREIGN KEY (`gno`) REFERENCES `goodsinfo` (`gno`),
  CONSTRAINT `FK_cartInfo_mno` FOREIGN KEY (`mno`) REFERENCES `memberinfo` (`mno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of cartinfo
-- ----------------------------
INSERT INTO cartinfo VALUES ('657da936c4374f5793475c1e0b43aa6f', '1', '6', '1');
INSERT INTO cartinfo VALUES ('e50768b154964783aba5ad5867af5771', '1', '7', '3');

-- ----------------------------
-- Table structure for `goodsinfo`
-- ----------------------------
DROP TABLE IF EXISTS `goodsinfo`;
CREATE TABLE `goodsinfo` (
  `gno` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(100) COLLATE utf8_bin NOT NULL,
  `tno` int(11) DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `intro` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  `pics` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `unit` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `qperied` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `weight` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `descr` text COLLATE utf8_bin,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`gno`),
  KEY `FK_goodsInfo_tno` (`tno`),
  CONSTRAINT `FK_goodsInfo_tno` FOREIGN KEY (`tno`) REFERENCES `goodstype` (`tno`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of goodsinfo
-- ----------------------------
INSERT INTO goodsinfo VALUES ('1', '开心果', '101', '68.00', '三只松鼠 开心果500g每日坚果休闲', '99', '../pics/1589957297305_1003.jpg;../pics/1589957297309_1001.jpg', '包', '6个月', '500G', 0x3C70207374796C653D22746578742D616C69676E3A63656E746572223E3C696D6720616C743D2222207372633D222E2E2F2E2E2F2E2E2F706963732F313538393935373032373932375F313030322E6A706722207374796C653D226865696768743A3132353970783B2077696474683A373930707822202F3E3C2F703E0D0A0D0A3C70207374796C653D22746578742D616C69676E3A63656E746572223EE4BAB2E4BBACE580BCE5BE97E68BA5E69C893C2F703E0D0A0D0A3C70207374796C653D22746578742D616C69676E3A63656E746572223E3C696D6720616C743D2222207372633D222E2E2F2E2E2F2E2E2F706963732F313538393935373239303338315F313030342E6A706722207374796C653D226865696768743A39393070783B2077696474683A373930707822202F3E3C2F703E0D0A, '1');
INSERT INTO goodsinfo VALUES ('2', '开口松子', '101', '48.00', '【三只松鼠_开口松子160g】干果', '115', '../pics/1589957593619_1007.jpg;../pics/1589957593623_1005.jpg', '包', '6个月', '160G', 0x3C70207374796C653D22746578742D616C69676E3A63656E746572223E3C696D6720616C743D2222207372633D222E2E2F2E2E2F2E2E2F706963732F313538393935373533323931315F313030362E6A706722207374796C653D226865696768743A3131353070783B2077696474683A373930707822202F3E3C2F703E0D0A, '1');
INSERT INTO goodsinfo VALUES ('3', '炭烧腰果', '101', '68.00', '三只松鼠炭烧腰果100g休闲食品零食坚', '192', '../pics/1589957666208_1008.jpg', '包', '6个月', '100G', 0x3C70207374796C653D22746578742D616C69676E3A63656E746572223E3C696D6720616C743D2222207372633D222E2E2F2E2E2F2E2E2F706963732F313538393935373636313834375F313030392E6A706722207374796C653D226865696768743A3131363170783B2077696474683A373930707822202F3E3C2F703E0D0A, '1');
INSERT INTO goodsinfo VALUES ('4', '碧根果', '101', '43.00', '【三只松鼠_碧根果160g】零食坚果干果山核桃奶油味散装袋装', '298', '../pics/1589957727877_1010.jpg', '包', '6个月', '160G', 0x3C70207374796C653D22746578742D616C69676E3A63656E746572223E3C696D6720616C743D2222207372633D222E2E2F2E2E2F2E2E2F706963732F313538393935373732313639395F313031312E6A706722207374796C653D226865696768743A3135363370783B2077696474683A373930707822202F3E3C2F703E0D0A, '1');
INSERT INTO goodsinfo VALUES ('5', '安慕希风味酸牛奶', '102', '70.00', '伊利安慕希风味酸牛奶原味205g*12+4盒/整箱', '98', '../pics/1589957975356_1012.jpg', '箱', '30天', '3.2KG', 0x3C70207374796C653D22746578742D616C69676E3A63656E746572223E3C696D6720616C743D2222207372633D222E2E2F2E2E2F2E2E2F706963732F313538393935373837393536335F313031332E6A706722207374796C653D226865696768743A34333070783B2077696474683A373439707822202F3E3C2F703E0D0A, '1');
INSERT INTO goodsinfo VALUES ('6', '喜之郎果肉果冻', '103', '36.80', '喜之郎果肉果冻大杯装200g*10杯什锦大礼包整箱儿童布丁零食批发', '200', '../pics/1589958138486_1015.jpg;../pics/1589958138489_1014.jpg', '箱', '2个月', '2KG', 0x3C70207374796C653D22746578742D616C69676E3A63656E746572223E3C696D6720616C743D2222207372633D222E2E2F2E2E2F2E2E2F706963732F313538393935383036323831355F313031362E6A706722207374796C653D226865696768743A37323870783B2077696474683A373530707822202F3E3C2F703E0D0A, '1');
INSERT INTO goodsinfo VALUES ('7', '大白兔奶糖', '104', '21.80', '大白兔奶糖500g散装礼盒正品原味婚庆儿童节糖果结婚喜糖', '200', '../pics/1589958248105_1018.jpg;../pics/1589958248107_1017.jpg', '盒', '3个月', '500G', 0x3C70207374796C653D22746578742D616C69676E3A63656E746572223E3C696D6720616C743D2222207372633D222E2E2F2E2E2F2E2E2F706963732F313538393935383234353837315F313031392E6A706722207374796C653D226865696768743A3131333170783B2077696474683A373930707822202F3E3C2F703E0D0A, '1');

-- ----------------------------
-- Table structure for `goodstype`
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
  `tno` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(100) COLLATE utf8_bin NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`tno`),
  UNIQUE KEY `tname` (`tname`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO goodstype VALUES ('101', '坚果', '0');
INSERT INTO goodstype VALUES ('102', '奶制品', '1');
INSERT INTO goodstype VALUES ('103', '果冻', '1');
INSERT INTO goodstype VALUES ('104', '糖果', '1');
INSERT INTO goodstype VALUES ('105', '麻辣', '1');

-- ----------------------------
-- Table structure for `memberinfo`
-- ----------------------------
DROP TABLE IF EXISTS `memberinfo`;
CREATE TABLE `memberinfo` (
  `mno` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(100) COLLATE utf8_bin NOT NULL,
  `realName` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `pwd` varchar(100) COLLATE utf8_bin NOT NULL,
  `tel` varchar(15) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `photo` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `regDate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`mno`),
  UNIQUE KEY `nickName` (`nickName`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `tel` (`tel`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of memberinfo
-- ----------------------------
INSERT INTO memberinfo VALUES ('1', 'navy', '', 'c8837b23ff8aaa8a2dde915473ce0991', '15096098010', '278224975@qq.com', '', '2020-05-16 15:32:15', '1');
INSERT INTO memberinfo VALUES ('2', '源辰', null, 'c8837b23ff8aaa8a2dde915473ce0991', '15096098011', '278224976@qq.com', null, '2020-05-19 16:48:16', '1');
INSERT INTO memberinfo VALUES ('3', 'yc', null, 'c8837b23ff8aaa8a2dde915473ce0991', '15096098012', '278224977@qq.com', null, '2020-05-19 16:48:49', '1');

-- ----------------------------
-- Table structure for `orderinfo`
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `ono` varchar(100) COLLATE utf8_bin NOT NULL,
  `odate` datetime DEFAULT NULL,
  `ano` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `sdate` datetime DEFAULT NULL,
  `rdate` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `invoice` int(11) DEFAULT NULL,
  PRIMARY KEY (`ono`),
  KEY `FK_orderInfo_ano` (`ano`),
  CONSTRAINT `FK_orderInfo_ano` FOREIGN KEY (`ano`) REFERENCES `addrinfo` (`ano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO orderinfo VALUES ('15901154123972994', '2020-05-22 10:43:32', '1590053041766', null, null, '2', '794.00', '0');
INSERT INTO orderinfo VALUES ('1590118093002512', '2020-05-22 11:28:13', '1590052579423', null, null, '2', '218.00', '0');
INSERT INTO orderinfo VALUES ('15901182400373787', '2020-05-22 11:30:40', '1590053187661', null, null, '2', '96.00', '0');

-- ----------------------------
-- Table structure for `orderiteminfo`
-- ----------------------------
DROP TABLE IF EXISTS `orderiteminfo`;
CREATE TABLE `orderiteminfo` (
  `ino` int(11) NOT NULL AUTO_INCREMENT,
  `ono` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `gno` int(11) DEFAULT NULL,
  `nums` int(11) DEFAULT NULL,
  `price` decimal(8,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`ino`),
  KEY `FK_orderItemInfo_ono` (`ono`),
  KEY `FK_orderItemInfo_gno` (`gno`),
  CONSTRAINT `FK_orderItemInfo_gno` FOREIGN KEY (`gno`) REFERENCES `goodsinfo` (`gno`),
  CONSTRAINT `FK_orderItemInfo_ono` FOREIGN KEY (`ono`) REFERENCES `orderinfo` (`ono`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of orderiteminfo
-- ----------------------------
INSERT INTO orderiteminfo VALUES ('10', '15901154123972994', '2', '5', '48.00', '1');
INSERT INTO orderiteminfo VALUES ('11', '15901154123972994', '3', '8', '68.00', '1');
INSERT INTO orderiteminfo VALUES ('13', '1590118093002512', '5', '2', '70.00', '1');
INSERT INTO orderiteminfo VALUES ('14', '1590118093002512', '1', '1', '68.00', '1');
INSERT INTO orderiteminfo VALUES ('16', '15901182400373787', '4', '2', '43.00', '1');
