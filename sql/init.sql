-- ============================================
-- 实验一：数据库初始化脚本（自定义员工数据）
-- 说明：为避免与其他同学使用完全一致的实验数据，
--       员工信息已作个性化修改，结构与字段保持不变。
-- 执行方式：在 MySQL 中整体执行本脚本即可（可重复执行）。
-- ============================================

CREATE DATABASE IF NOT EXISTS ssm_emp DEFAULT CHARACTER SET utf8mb4;

USE ssm_emp;

-- 若表已存在则先删除，保证脚本可重复执行
DROP TABLE IF EXISTS emp;

CREATE TABLE emp (
    emp_id     INT PRIMARY KEY AUTO_INCREMENT COMMENT '员工编号',
    emp_name   VARCHAR(50)  NOT NULL COMMENT '姓名',
    gender     CHAR(1)      DEFAULT '男' COMMENT '性别',
    dept       VARCHAR(50)  COMMENT '部门',
    post       VARCHAR(50)  COMMENT '岗位',
    salary     DECIMAL(10,2) COMMENT '薪资',
    hire_date  DATE         COMMENT '入职时间',
    status     TINYINT      DEFAULT 1 COMMENT '状态：1在职 0离职'
);

INSERT INTO emp (emp_name, gender, dept, post, salary, hire_date, status) VALUES
('张思远', '男', '技术部', '后端开发工程师', 15000.00, '2023-09-01', 1),
('林晓彤', '女', '产品部', '产品经理', 13000.00, '2024-03-15', 1),
('刘洋', '男', '运营部', '运营专员', 8500.00, '2022-11-20', 1),
('周雨欣', '女', '财务部', '财务主管', 11000.00, '2021-07-08', 0),
('黄子轩', '男', '技术部', '算法工程师', 18000.00, '2025-01-10', 1),
('吴倩', '女', '市场部', '新媒体运营', 9500.00, '2024-06-01', 1);
