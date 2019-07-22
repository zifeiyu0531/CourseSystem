运行方法
0.打开MySQL服务
1.在MySQL环境下执行CourseSystem.sql文件
2.使用IDEA打开CourseSystem目录
3.将CourseSystem\src\DataSource目录下的所有代码文件内的private String PASSWORD="123456"; 改为用户对应的MySQL密码
4.运行项目

功能介绍
实现了带图形界面的课程管理系统，包括管理员的登录与注册，班级学生的增删改查，课程的增删改查，课程内学生的增删改查

项目结构
程序入口：src\CourseSystem\CourseSystem.java
文件目录：
	Adapters：窗口事件定义（窗口关闭等）
	Bean：基本类定义（用户，学生，课程等）
	CourseSystem：程序入口
	DataSource：有关数据库操作的类（增删改查）
	Forms：所有窗体类