# <p align="center"><img src="asserts/logo.png" alt="WIFP Logo" width="120" /></p>

# <p align="center">WIFP-Servlet</p>

<p align="center">一个基于 Java Web（Servlet/JSP）的巡检互动反馈平台</p>
<p align="center"><a href="./README_EN.md">English</a> | <strong>中文</strong></p>

---

## 项目介绍

WIFP-Servlet（Wisteria Inspection Feedback Platform-Servlet）是一个用于“巡检记录上报 + 反馈沟通 + 分类/标签管理”的轻量级 JavaWeb 项目。

说明：这是我在学校学习 JavaWeb 课程时完成的学生项目，早期实现比较直白，BUG 也可能不少。毕业后做了一轮整理，心血来潮把它发布出来，供学习交流使用，不喜勿喷。

## 系统功能（按业务模块）

### 1) 巡检反馈（Note）

- 巡检反馈列表：按时间倒序展示巡检反馈信息
- 阅读巡检反馈详情：展示标题、正文、作者、发布时间、浏览次数、类别、标签、评论列表
- 浏览次数统计：每次阅读详情会自动 +1
- 关键词搜索：支持按标题/正文/类别等关键字模糊查询
- 按类别筛选：点击类别可查看该类别下的巡检反馈列表
- 按标签筛选：点击标签可查看该标签下的巡检反馈列表

### 2) 评论反馈（Comment）

- 发表评论：对某条巡检反馈提交评论（标题 + 内容）
- 记录评论来源 IP：服务端记录 remoteIP
- 我的反馈：已登录用户可查看自己发表过的评论列表

### 3) 用户体系（Users）

- 用户注册：包含验证码校验与用户名可用性校验
- 用户登录：支持“记住我”（通过 Cookie 记住账号信息）
- 用户注销：清理会话信息
- 修改密码：登录后可修改密码
- 个人资料维护：昵称、电话、个人简介等
- 头像上传/展示：上传头像文件并在个人卡片区域展示

### 4) 后台管理（Author / 管理员）

说明：系统将 `isAuthor = 'Y'` 的用户视为“后台管理/作者”角色。

- 新增巡检记录：创建巡检反馈（标题、正文、类别、标签等）
- 编辑巡检记录：修改巡检反馈内容并记录更新时间
- 删除巡检记录：删除巡检反馈，同时清理对应评论/标签关联
- 巡检类别管理：查看/编辑类别名称（会同步更新相关巡检记录）
- 巡检标签管理：查看/编辑标签名称（会同步更新标签表）
- 个人信息维护：后台角色可维护个人信息

## 技术栈

### 后端

- Java 17
- Servlet 6（`jakarta.servlet-api 6.1.0`）
- JSP + JSTL
- JDBC（手写 SQL，未使用 MyBatis）
- Maven（`war` 打包）

### 前端

- Layui（UI 框架）
- CKEditor 5（富文本编辑器）

### 数据库

- MySQL（脚本与驱动均兼容 5.7/8.0 常见版本）

## 快速开始

### 1) 准备环境

- JDK 17（本项目使用 Java 17 编译）
- Tomcat 10.1+（Servlet 6 / Jakarta 命名空间）
- MySQL 5.7+ / 8.0+

### 2) 初始化数据库

项目根目录提供初始化脚本：

- `wifp.sql`（会创建并使用 `wifp` 数据库，并初始化示例数据）

执行方式（任选其一）：

- 使用 Navicat / DataGrip 导入执行 `wifp.sql`
- 终端执行：`mysql -u  -p < wifp.sql`

### 3) 修改数据库连接

修改数据库配置文件：

- `src/main/resources/db.properties`

### 4) 构建并部署

在项目目录下执行 Maven Wrapper 打包（Windows）：

- `mvnw.cmd -DskipTests package`

生成产物：

- `target/WIFP-Servlet.war`

将 `WIFP-Servlet.war` 放入 Tomcat 的 `webapps` 目录，启动 Tomcat 后访问即可。

### 5) 补充说明

目前数据库中所有用户的密码都是123456

## 项目结构

- `src/main/java/com/wisteria/controller`：Servlet 控制器（路由入口）
- `src/main/java/com/wisteria/service`：业务逻辑层
- `src/main/java/com/wisteria/mapper`：JDBC 数据访问层（SQL 写在 Java 代码中）
- `src/main/webapp`：JSP 页面与静态资源（Layui / CKEditor / 图片等）

## 效果图

<div align="center">
  <table align="center">
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%871.png" alt="效果图1" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%872.png" alt="效果图2" width="420" />
      </td>
    </tr>
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%873.png" alt="效果图3" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%874.png" alt="效果图4" width="420" />
      </td>
    </tr>
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%875.png" alt="效果图5" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%876.png" alt="效果图6" width="420" />
      </td>
    </tr>
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%877.png" alt="效果图7" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%878.png" alt="效果图8" width="420" />
      </td>
    </tr>
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%879.png" alt="效果图9" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%8710.png" alt="效果图10" width="420" />
      </td>
    </tr>
    <tr>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%8711.png" alt="效果图11" width="420" />
      </td>
      <td align="center">
        <img src="asserts/%E5%9B%BE%E7%89%8712.png" alt="效果图12" width="420" />
      </td>
    </tr>
  </table>
</div>

## 免责声明

- 本项目用于学习与交流，代码实现以“能跑起来”为主要目标，工程化与安全性并非最佳实践
- 若你在使用过程中发现问题，欢迎提 Issue / PR；如果只是来吐槽，也请手下留情

