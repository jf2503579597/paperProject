/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_local
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : paper_db

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 18/05/2021 15:20:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (3, '教师');
INSERT INTO `authority` VALUES (2, '科研秘书');
INSERT INTO `authority` VALUES (1, '管理员');

-- ----------------------------
-- Table structure for authority_menu
-- ----------------------------
DROP TABLE IF EXISTS `authority_menu`;
CREATE TABLE `authority_menu`  (
  `auth_id` int NULL DEFAULT NULL COMMENT '权限id',
  `menu_id` int NULL DEFAULT NULL COMMENT '菜单id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限-菜单信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority_menu
-- ----------------------------
INSERT INTO `authority_menu` VALUES (1, 1);
INSERT INTO `authority_menu` VALUES (1, 3);
INSERT INTO `authority_menu` VALUES (1, 2);
INSERT INTO `authority_menu` VALUES (1, 4);
INSERT INTO `authority_menu` VALUES (1, 5);
INSERT INTO `authority_menu` VALUES (1, 6);
INSERT INTO `authority_menu` VALUES (1, 7);
INSERT INTO `authority_menu` VALUES (1, 8);
INSERT INTO `authority_menu` VALUES (1, 9);
INSERT INTO `authority_menu` VALUES (1, 10);
INSERT INTO `authority_menu` VALUES (1, 11);
INSERT INTO `authority_menu` VALUES (1, 12);
INSERT INTO `authority_menu` VALUES (1, 13);
INSERT INTO `authority_menu` VALUES (1, 14);
INSERT INTO `authority_menu` VALUES (1, 15);
INSERT INTO `authority_menu` VALUES (1, 16);
INSERT INTO `authority_menu` VALUES (1, 17);
INSERT INTO `authority_menu` VALUES (1, 19);
INSERT INTO `authority_menu` VALUES (1, 21);
INSERT INTO `authority_menu` VALUES (1, 22);
INSERT INTO `authority_menu` VALUES (2, 1);
INSERT INTO `authority_menu` VALUES (2, 7);
INSERT INTO `authority_menu` VALUES (2, 8);
INSERT INTO `authority_menu` VALUES (2, 5);
INSERT INTO `authority_menu` VALUES (2, 17);
INSERT INTO `authority_menu` VALUES (2, 19);
INSERT INTO `authority_menu` VALUES (2, 21);
INSERT INTO `authority_menu` VALUES (2, 22);
INSERT INTO `authority_menu` VALUES (3, 1);
INSERT INTO `authority_menu` VALUES (3, 7);
INSERT INTO `authority_menu` VALUES (3, 8);
INSERT INTO `authority_menu` VALUES (3, 5);
INSERT INTO `authority_menu` VALUES (3, 18);
INSERT INTO `authority_menu` VALUES (3, 19);
INSERT INTO `authority_menu` VALUES (3, 20);
INSERT INTO `authority_menu` VALUES (2, 6);
INSERT INTO `authority_menu` VALUES (1, 23);

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `coll_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学院',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES (1, '电子信息与电气工程学院');
INSERT INTO `college` VALUES (2, '人文学院');
INSERT INTO `college` VALUES (3, '化材院');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `major_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '专业',
  `coll_id` int NULL DEFAULT NULL COMMENT '所属院系',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '专业信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES (1, '电子信息科学与技术', 1);
INSERT INTO `major` VALUES (2, '学前教育', 2);
INSERT INTO `major` VALUES (3, '电气工程及其自动化', 1);
INSERT INTO `major` VALUES (5, '化学', 3);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int NULL DEFAULT NULL COMMENT '上级菜单id',
  `text` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单跳转路由（二级菜单所具有）',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '首页', '', 'fa fa-cloud', '2021-04-18 22:53:02', '2021-04-20 21:28:05');
INSERT INTO `menu` VALUES (2, 0, '论文基本信息维护', NULL, 'fa fa-cloud', '2021-04-18 22:53:22', '2021-04-20 21:28:06');
INSERT INTO `menu` VALUES (3, 0, '教师基本信息维护', NULL, 'fa fa-cloud', '2021-04-18 22:53:34', '2021-04-20 21:28:06');
INSERT INTO `menu` VALUES (4, 0, '教师管理', NULL, 'fa fa-cloud', '2021-04-18 22:53:43', '2021-04-20 21:28:07');
INSERT INTO `menu` VALUES (5, 0, '论文管理', NULL, 'fa fa-cloud', '2021-04-18 22:53:48', '2021-04-20 21:28:07');
INSERT INTO `menu` VALUES (6, 0, '图表分析', NULL, 'fa fa-cloud', '2021-04-18 22:53:52', '2021-04-20 21:28:08');
INSERT INTO `menu` VALUES (7, 1, '审核通知', 'first/exam', 'fa fa-cloud', '2021-04-18 22:54:11', '2021-04-21 12:12:26');
INSERT INTO `menu` VALUES (8, 1, '修改密码', 'first/update_password', 'fa fa-cloud', '2021-04-18 22:54:17', '2021-04-21 12:12:43');
INSERT INTO `menu` VALUES (9, 2, '学科信息维护', 'paper_infor/subject', 'fa fa-cloud', '2021-04-18 22:54:27', '2021-04-21 12:17:02');
INSERT INTO `menu` VALUES (10, 2, '项目来源维护', 'paper_infor/project_source', 'fa fa-cloud', '2021-04-18 22:55:03', '2021-04-21 12:17:05');
INSERT INTO `menu` VALUES (11, 2, '期刊信息维护', 'paper_infor/period', 'fa fa-cloud', '2021-04-18 22:55:37', '2021-04-21 12:17:10');
INSERT INTO `menu` VALUES (13, 3, '专业信息维护', 'teacher_infor/major', 'fa fa-cloud', '2021-04-18 22:55:55', '2021-04-21 12:15:35');
INSERT INTO `menu` VALUES (14, 3, '职称信息维护', 'teacher_infor/professor', 'fa fa-cloud', '2021-04-18 22:56:03', '2021-04-21 12:15:51');
INSERT INTO `menu` VALUES (15, 4, '教师信息添加', 'teacher/add_teacher', 'fa fa-cloud', '2021-04-18 22:56:16', '2021-04-21 12:16:30');
INSERT INTO `menu` VALUES (16, 4, '教师信息查询', 'teacher/find_teacher', 'fa fa-cloud', '2021-04-18 22:56:26', '2021-04-21 12:16:38');
INSERT INTO `menu` VALUES (17, 5, '审核论文', 'paper/check', 'fa fa-cloud', '2021-04-18 22:56:38', '2021-04-21 12:17:38');
INSERT INTO `menu` VALUES (18, 5, '上传论文', 'paper/upload', 'fa fa-cloud', '2021-04-18 22:56:55', '2021-04-21 12:17:53');
INSERT INTO `menu` VALUES (19, 5, '论文查询', 'paper/find_paper', 'fa fa-cloud', '2021-04-18 22:57:06', '2021-04-21 12:18:02');
INSERT INTO `menu` VALUES (20, 5, '我的论文', 'paper/mypaper', 'fa fa-cloud', '2021-04-18 22:57:13', '2021-04-21 12:18:10');
INSERT INTO `menu` VALUES (21, 6, '专业图表分析', 'chart/chart_analy', 'fa fa-cloud', '2021-04-18 22:57:28', '2021-04-21 12:18:59');
INSERT INTO `menu` VALUES (22, 6, '期刊等级论文统计', 'chart/statis', 'fa fa-cloud', '2021-04-18 22:57:43', '2021-04-21 12:19:32');
INSERT INTO `menu` VALUES (23, 3, '学院信息维护', 'teacher_infor/college', 'fa fa-cloud', '2021-05-03 21:46:46', '2021-05-04 00:44:46');

-- ----------------------------
-- Table structure for paper
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '题目',
  `period_id` int NULL DEFAULT NULL COMMENT '期刊类别',
  `period_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '期号，依次递增',
  `sub_id` int NULL DEFAULT NULL COMMENT '学科信息',
  `perid_level_id` int NULL DEFAULT NULL COMMENT '期刊级别',
  `user_id` int NOT NULL COMMENT '作者id',
  `project_source_id` int NULL DEFAULT NULL COMMENT '项目来源',
  `exam` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核状态(已审核，未审核)',
  `exam_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审核失败理由',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `download_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '下载路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '论文信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of paper
-- ----------------------------
INSERT INTO `paper` VALUES (1, '教师科研论文管理系统', 1, '1008613', 2, 1, 3, 1, '已审核', NULL, '2021-04-28 11:06:55', '2021-04-28 16:38:20', '/work/66a11152-027c-4505-b1e4-8ae71779d867.docx');
INSERT INTO `paper` VALUES (11, '道德观与价值观的初步交互', 2, '1008614', 1, 1, 3, 2, '已审核', NULL, '2021-04-28 16:39:49', '2021-05-16 22:47:04', '/work/e1fdc940-cafd-4bf1-9408-3827410ad845.docx');
INSERT INTO `paper` VALUES (12, '论如今环境能否修真', 1, '1008615', 2, 1, 3, 2, '已审核', NULL, '2021-04-28 16:44:15', '2021-05-16 22:47:16', '/work/67714c60-7888-4a1d-9bfe-fa58327f9cda.txt');
INSERT INTO `paper` VALUES (14, '修真界与仙界是否存在论证', 2, '1008616', 1, 2, 3, 1, '审核失败', '名字太难听了，该个新名字在申请吧！', '2021-05-05 17:03:58', '2021-05-16 22:47:33', '/work/ad8e6202-cd96-47bb-b172-849703be5bae.PDM');
INSERT INTO `paper` VALUES (15, '末法时代修真者该何去何从', 1, '1008617', 1, 1, 3, 1, '审核失败', '和驾乘是个傻狗', '2021-05-15 21:53:00', '2021-05-16 22:48:49', '/work/7dc80724-6439-4860-800e-a32e74ba61a7.docx');
INSERT INTO `paper` VALUES (16, '达尔文进化论的再次探究', 5, '1008618', 2, 8, 3, 1, '未审核', NULL, '2021-05-16 22:33:02', '2021-05-16 22:33:02', '/work/5774c63c-ab52-4cc1-9e7f-b36b75392904.docx');

-- ----------------------------
-- Table structure for periodical
-- ----------------------------
DROP TABLE IF EXISTS `periodical`;
CREATE TABLE `periodical`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `period_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '刊物类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '期刊类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of periodical
-- ----------------------------
INSERT INTO `periodical` VALUES (1, '自然科学');
INSERT INTO `periodical` VALUES (2, '农学');
INSERT INTO `periodical` VALUES (5, '生物学');

-- ----------------------------
-- Table structure for periodical_level
-- ----------------------------
DROP TABLE IF EXISTS `periodical_level`;
CREATE TABLE `periodical_level`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `level_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '刊物级别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '期刊级别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of periodical_level
-- ----------------------------
INSERT INTO `periodical_level` VALUES (1, '特种刊物论文');
INSERT INTO `periodical_level` VALUES (2, '权威核心刊物论文');
INSERT INTO `periodical_level` VALUES (3, '重要核心刊物论文');
INSERT INTO `periodical_level` VALUES (4, '一般核心刊物论文');
INSERT INTO `periodical_level` VALUES (5, '一般公开刊物论文');
INSERT INTO `periodical_level` VALUES (6, '受限公开刊物论文');
INSERT INTO `periodical_level` VALUES (7, '国家级内刊');
INSERT INTO `periodical_level` VALUES (8, '省级内刊');
INSERT INTO `periodical_level` VALUES (9, '区级内刊');

-- ----------------------------
-- Table structure for professor
-- ----------------------------
DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `professor_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职称信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '职称信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of professor
-- ----------------------------
INSERT INTO `professor` VALUES (1, '正高级');
INSERT INTO `professor` VALUES (2, '高级');
INSERT INTO `professor` VALUES (3, '一级');
INSERT INTO `professor` VALUES (4, '二级');
INSERT INTO `professor` VALUES (5, '三级');

-- ----------------------------
-- Table structure for project_source
-- ----------------------------
DROP TABLE IF EXISTS `project_source`;
CREATE TABLE `project_source`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `source_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '来源名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '项目来源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_source
-- ----------------------------
INSERT INTO `project_source` VALUES (1, '学校项目');
INSERT INTO `project_source` VALUES (2, '省级项目');
INSERT INTO `project_source` VALUES (3, '国家级项目');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sub_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学科信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学科信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES (1, '工科');
INSERT INTO `subject` VALUES (2, '理科');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `card` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工号(登录账号)',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `birthday` date NULL DEFAULT NULL COMMENT '出生年月',
  `professor_id` int NULL DEFAULT NULL COMMENT '职称主键',
  `college_id` int NULL DEFAULT NULL COMMENT '学院主键',
  `major_id` int NULL DEFAULT NULL COMMENT '专业主键',
  `auth_id` int NULL DEFAULT NULL COMMENT '权限',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人员信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '管理员', '男', 'admin', '1998-02-11', NULL, NULL, NULL, 1, '2021-04-20 10:38:53', '2021-04-29 11:45:52');
INSERT INTO `user` VALUES (2, '1001', '科研秘书', '女', '1001', '1995-10-27', NULL, NULL, NULL, 2, '2021-04-20 10:39:37', '2021-04-22 17:25:00');
INSERT INTO `user` VALUES (3, '1002', '叶问', '男', '1002', '1995-05-21', 1, 1, 1, 3, '2021-04-20 10:41:12', '2021-04-22 09:06:09');
INSERT INTO `user` VALUES (4, '1003', '陈北玄', '男', '1003', '2021-04-25', 1, 2, 2, 3, '2021-04-25 17:34:58', '2021-04-25 17:34:58');
INSERT INTO `user` VALUES (8, '1004', '苏静雯', '男', '1004', '2000-02-26', 4, 3, 5, 3, '2021-05-16 23:00:14', '2021-05-16 23:00:14');

SET FOREIGN_KEY_CHECKS = 1;
