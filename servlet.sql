/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_5.7
 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Host           : localhost:3305
 Source Schema         : servlet

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 14/07/2023 13:31:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别（0：未知、1：男、2：女）',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `create_by` int(11) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1100210 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '1234', '1', 18, '13111111111', 1, '2004-04-07 00:00:00');
INSERT INTO `user` VALUES (5, 'Li Yunxi', '1234', '2', 51, '18510213828', 1, '2002-04-20 13:56:56');
INSERT INTO `user` VALUES (6, 'Dong Zitao', 'r1QgUuzF5k', '2', 40, '7697273466', 1, '2011-06-03 17:56:12');
INSERT INTO `user` VALUES (7, '尹致远', 'Jqp2ZrKY5Y', '2', 21, '7550021459', 1, '2019-06-24 22:18:46');
INSERT INTO `user` VALUES (11, 'Chiang Ka Man', '7S1KF6VH2u', '1', 64, '19484935822', 1, '2003-07-06 22:36:41');
INSERT INTO `user` VALUES (12, '袁岚', 'WYM2AheHDr', '1', 60, '13627830933', 1, '2003-07-28 14:52:01');
INSERT INTO `user` VALUES (13, 'Tang Wing Fat', 'qmed7KYAtr', '1', 54, '103736104', 1, '2002-10-06 01:32:54');
INSERT INTO `user` VALUES (14, 'Cheung Wai Lam', 'iwpu2BZciR', '1', 50, '216153186', 1, '2007-04-10 10:24:09');
INSERT INTO `user` VALUES (16, '蒋宇宁', 'yLh242VmWp', '1', 20, '1071558877', 1, '2019-03-03 16:51:21');
INSERT INTO `user` VALUES (17, 'Ashley Roberts', 'ytHPwk8mJO', '0', 25, '14092453499', 1, '2000-09-28 12:21:01');
INSERT INTO `user` VALUES (18, 'Song Shihan', '4F3aWWpsuK', '0', 43, '102424525', 1, '2016-10-31 21:54:15');
INSERT INTO `user` VALUES (19, 'Sugiyama Yamato', 'MqFzoooGUm', '2', 37, '7698498674', 1, '2005-01-21 21:03:07');
INSERT INTO `user` VALUES (20, 'Su Jialun', 'xY5MnbHxS5', '0', 62, '7695828205', 1, '2002-01-22 04:10:47');
INSERT INTO `user` VALUES (21, 'Murata Aoi', 'hG1amiGmHN', '1', 54, '16784321315', 1, '2015-02-19 06:06:05');
INSERT INTO `user` VALUES (22, 'Endo Takuya', 'aBQIjJzS49', '2', 64, '16698747056', 1, '2004-11-18 09:42:03');
INSERT INTO `user` VALUES (23, '杨岚', 'AUCzQgW7d1', '0', 30, '13615362429', 1, '2022-10-12 07:36:26');
INSERT INTO `user` VALUES (24, 'Sakamoto Nanami', 'svddbaFCYl', '1', 32, '19332298368', 1, '2014-09-29 09:51:38');
INSERT INTO `user` VALUES (25, 'Jean Barnes', 'PLMIf5GTM9', '0', 42, '17106697140', 1, '2001-11-28 17:28:34');
INSERT INTO `user` VALUES (26, 'Joyce Davis', 'A9PwBWuOsm', '1', 62, '1042501439', 1, '2023-01-08 13:38:53');
INSERT INTO `user` VALUES (27, 'Steve Payne', 'CczfJrXixr', '1', 62, '18109824223', 1, '2004-07-12 14:55:10');
INSERT INTO `user` VALUES (28, 'Han Lu', 'GpRZuI2qXR', '1', 44, '14839070398', 1, '2006-12-06 10:20:04');
INSERT INTO `user` VALUES (29, '朱震南', 'ELooExTyXA', '0', 25, '217793731', 1, '2015-05-09 02:20:44');
INSERT INTO `user` VALUES (30, 'Ishii Takuya', 'ix7fmiEo4W', '1', 46, '19007197920', 1, '2015-04-30 19:15:04');
INSERT INTO `user` VALUES (31, 'Song Yunxi', 'zKlsdtTsTM', '2', 31, '285054333', 1, '2003-05-14 05:53:52');
INSERT INTO `user` VALUES (32, '丁嘉伦', 'V03CNva40k', '1', 40, '2055252061', 1, '2009-06-08 06:24:55');
INSERT INTO `user` VALUES (33, 'Huang Zitao', 'qqO2ZqZcIf', '0', 45, '16654072798', 1, '2016-11-03 18:47:57');
INSERT INTO `user` VALUES (34, 'Hayashi Hikari', 'S3BTID0FYd', '1', 51, '13735869638', 1, '2016-10-03 13:45:33');
INSERT INTO `user` VALUES (35, '阎云熙', 'NDhIUBBwPF', '2', 37, '200358750', 1, '2008-10-20 17:36:28');
INSERT INTO `user` VALUES (36, '常晓明', 'QlxU1GuN4F', '1', 33, '214619160', 1, '2000-06-13 10:00:31');
INSERT INTO `user` VALUES (37, '阎詩涵', 'lxda1KNHFY', '2', 56, '14114405118', 1, '2007-10-29 21:38:26');
INSERT INTO `user` VALUES (38, 'Nakano Riku', 'JYtiQqRDsC', '1', 58, '217372877', 1, '2023-01-19 23:49:06');
INSERT INTO `user` VALUES (39, '于震南', 'trO6DJi9eY', '1', 22, '19223511099', 1, '2003-03-08 22:47:31');
INSERT INTO `user` VALUES (40, 'Han Zhennan', 'F2OPWbjbnv', '2', 54, '13196072322', 1, '2009-07-20 20:44:54');
INSERT INTO `user` VALUES (41, 'Mak Sze Kwan', 'JPpDeBAsOT', '2', 24, '15165824167', 1, '2005-04-12 09:12:25');
INSERT INTO `user` VALUES (42, 'Mok Sze Kwan', 'KvjKTMvHim', '1', 39, '75564830667', 1, '2010-05-23 23:33:49');
INSERT INTO `user` VALUES (43, 'Hashimoto Mio', 'SHVkfRLlCg', '2', 38, '285407245', 1, '2015-04-30 00:30:20');
INSERT INTO `user` VALUES (44, 'Xiong Zhiyuan', 'atMSt7p6qT', '2', 49, '7609786549', 1, '2015-06-05 10:51:17');
INSERT INTO `user` VALUES (45, 'Takeuchi Airi', 'VWxwVixOjm', '1', 40, '289494424', 1, '2013-11-08 05:48:34');
INSERT INTO `user` VALUES (46, 'Au Tak Wah', 'VldawuCsID', '1', 20, '2825298458', 1, '2003-08-08 16:21:08');
INSERT INTO `user` VALUES (47, '邱杰宏', '1yqJzI5PU1', '0', 49, '7602584247', 1, '2013-06-20 04:13:27');
INSERT INTO `user` VALUES (48, '戴詩涵', 'ZCGOCYiXUK', '2', 60, '7557660127', 1, '2015-08-28 22:07:44');
INSERT INTO `user` VALUES (49, 'Shimizu Misaki', 'KvAlW4kiOw', '1', 42, '14120760976', 1, '2023-03-22 13:44:43');
INSERT INTO `user` VALUES (50, 'Arimura Mitsuki', '3Aerg3n9R7', '2', 44, '14424224113', 1, '2021-10-02 18:39:09');
INSERT INTO `user` VALUES (51, '阎岚', 'mODVQB8MZm', '1', 22, '76977520862', 1, '2016-11-28 07:28:58');
INSERT INTO `user` VALUES (52, 'Nakamori Kenta', 'QxTcQqGXUv', '1', 56, '16498043263', 1, '2010-03-16 02:01:18');
INSERT INTO `user` VALUES (53, 'Dong Lan', 'X7ER8lqe40', '0', 57, '2863855905', 1, '2018-12-21 11:16:53');
INSERT INTO `user` VALUES (54, 'Chung Chieh Lun', 'h0WthuMiAR', '0', 41, '14641352647', 1, '2023-01-27 15:00:02');
INSERT INTO `user` VALUES (55, 'Kam Sai Wing', 'rg1rvWR56C', '2', 52, '19271380925', 1, '2015-11-27 21:05:59');
INSERT INTO `user` VALUES (56, 'Liu Rui', 'YSdQKQn152', '1', 47, '19897064621', 1, '2013-12-14 10:37:13');
INSERT INTO `user` VALUES (57, 'Fu Lan', 'LxbVOnm6mg', '2', 43, '2057827408', 1, '2017-01-19 13:07:59');
INSERT INTO `user` VALUES (58, '黎宇宁', 'FnkI88EcZk', '1', 35, '7691390756', 1, '2012-06-23 03:46:23');
INSERT INTO `user` VALUES (59, 'Zou Jialun', '32M04R8Kda', '2', 23, '109733305', 1, '2003-05-16 07:04:27');
INSERT INTO `user` VALUES (60, '严震南', 'xFvFjJT53C', '0', 58, '7699723666', 1, '2018-12-17 14:53:46');
INSERT INTO `user` VALUES (61, 'Janice Fernandez', 'ubA8ZAArm5', '0', 54, '75555151635', 1, '2019-11-14 11:32:10');
INSERT INTO `user` VALUES (62, 'Chang Ziyi', 'Hq7GhBKg9O', '1', 26, '109265683', 1, '2006-04-22 20:02:28');
INSERT INTO `user` VALUES (63, 'Matsuda Airi', '4UO2XJ1Hnu', '1', 26, '2035116551', 1, '2001-08-05 03:19:15');
INSERT INTO `user` VALUES (64, 'Harada Mitsuki', '5vwNLd3Zfn', '1', 50, '17167668049', 1, '2010-09-22 12:46:57');
INSERT INTO `user` VALUES (65, 'Liao Kwok Ming', 'KWmHXVUPp5', '2', 23, '13329927184', 1, '2013-07-05 22:44:35');
INSERT INTO `user` VALUES (66, 'Feng Zhiyuan', '7li7MVKre5', '2', 51, '14633441900', 1, '2019-07-15 19:10:49');
INSERT INTO `user` VALUES (67, 'Christopher Moore', 'ab56ThR92d', '1', 59, '17458637269', 1, '2015-08-25 10:43:49');
INSERT INTO `user` VALUES (68, 'Kato Takuya', 'CEQ9WINc9D', '1', 50, '7602259905', 1, '2000-01-08 19:50:26');
INSERT INTO `user` VALUES (69, 'Carrie Patel', 'PZI0iQv2qW', '0', 33, '15467652827', 1, '2004-06-13 03:51:57');
INSERT INTO `user` VALUES (70, 'Inoue Takuya', 'QB7HFbacOY', '0', 49, '19805115087', 1, '2011-02-15 19:50:19');
INSERT INTO `user` VALUES (71, 'Ku Ka Fai', '7xB8XXEE8I', '0', 33, '18006767039', 1, '2019-12-14 18:32:19');
INSERT INTO `user` VALUES (72, 'Ying Yu Ling', 'BaJdDcrwrP', '2', 45, '14180197707', 1, '2003-05-21 21:23:15');
INSERT INTO `user` VALUES (73, 'Nakajima Hikaru', '9YhdbtSUFG', '1', 63, '104952937', 1, '2014-09-13 11:03:58');
INSERT INTO `user` VALUES (74, '刘致远', 'z6rkVJXvEA', '0', 23, '14866849024', 1, '2003-10-13 10:57:03');
INSERT INTO `user` VALUES (75, 'Norma Vargas', 'urKqRhVOLp', '1', 47, '16154660733', 1, '2019-10-12 23:23:54');
INSERT INTO `user` VALUES (76, 'George Boyd', 'HL5QeMj1Iu', '1', 40, '2051126187', 1, '2010-03-18 22:42:06');
INSERT INTO `user` VALUES (77, '许岚', 'gU74wOGmXn', '1', 60, '100348669', 1, '2007-11-10 04:54:17');
INSERT INTO `user` VALUES (78, '姚子异', '38uVzByvzh', '0', 29, '15966333069', 1, '2013-07-11 12:01:55');
INSERT INTO `user` VALUES (79, '许睿', 'IVu2JGXzFA', '2', 24, '18908900435', 1, '2018-06-01 06:11:33');
INSERT INTO `user` VALUES (80, 'Tsui Ming Sze', 'GXCVkUjI8B', '2', 64, '18349530068', 1, '2015-10-14 15:03:27');
INSERT INTO `user` VALUES (81, 'Chan Fat', 'svRMafaFtv', '0', 36, '2080712291', 1, '2012-03-11 22:33:21');
INSERT INTO `user` VALUES (82, 'Gao Lan', 'D2wqX6sQdG', '0', 23, '13859417584', 1, '2006-02-21 05:05:35');
INSERT INTO `user` VALUES (83, 'Pak Wing Kuen', 'GB7gQO17si', '0', 22, '13687939306', 1, '2006-12-30 08:12:33');
INSERT INTO `user` VALUES (84, 'Jia Zhiyuan', 'EnGVEHo3Vr', '2', 19, '14840771333', 1, '2020-12-26 18:08:56');
INSERT INTO `user` VALUES (85, 'Henry Gray', 'fO6HMrJlUV', '1', 57, '2087412337', 1, '2017-04-20 03:43:40');
INSERT INTO `user` VALUES (86, '金宇宁', 'xoFHGKEWma', '1', 44, '2083808959', 1, '2003-10-16 16:55:14');
INSERT INTO `user` VALUES (87, '邱子异', '1bE7AzVg2l', '0', 41, '15241420465', 1, '2008-01-25 19:12:17');
INSERT INTO `user` VALUES (88, 'Morita Eita', 'e1dyStFcPR', '2', 57, '14426020114', 1, '2020-09-02 05:04:54');
INSERT INTO `user` VALUES (89, 'Gu Lan', 'dKC5ThMkN9', '1', 52, '14919550635', 1, '2019-10-12 12:54:06');
INSERT INTO `user` VALUES (90, 'Yang Shihan', 'd34oOpqpVZ', '0', 33, '15375495379', 1, '2016-06-04 21:31:00');
INSERT INTO `user` VALUES (91, 'Marcus Soto', 'pfkg9M5uiv', '2', 34, '7609961814', 1, '2017-02-18 23:22:59');
INSERT INTO `user` VALUES (92, 'Tian Yuning', 'uQ8SvEWjM2', '0', 23, '16211917709', 1, '2008-12-02 12:06:27');
INSERT INTO `user` VALUES (93, '龚嘉伦', 'y5L6u6eKyL', '0', 40, '7699051528', 1, '2007-05-23 04:43:57');
INSERT INTO `user` VALUES (94, '谢晓明', 'l8guRNQ9m3', '0', 29, '7551210327', 1, '2015-09-15 12:01:32');
INSERT INTO `user` VALUES (95, 'Chow Yu Ling', 'ExgHdeqJMI', '2', 58, '15258391593', 1, '2015-08-01 19:52:41');
INSERT INTO `user` VALUES (96, 'Jane Lewis', 'netqEFfIBp', '0', 34, '76960080079', 1, '2009-01-20 23:08:45');
INSERT INTO `user` VALUES (97, '唐子韬', 'hkL1yuviJf', '1', 43, '75537535835', 1, '2012-10-17 12:34:42');
INSERT INTO `user` VALUES (98, 'Matsui Ikki', 'Wy2rQfW4Ln', '2', 63, '1099685539', 1, '2020-10-28 07:18:11');
INSERT INTO `user` VALUES (99, 'Cheng Suk Yee', 'WdfeWCOLlY', '0', 48, '13276567561', 1, '2012-11-03 18:41:04');
INSERT INTO `user` VALUES (100, 'Tao Zitao', 'f8SCaqlInE', '1', 47, '2137338283', 1, '2000-12-21 01:54:22');

SET FOREIGN_KEY_CHECKS = 1;
