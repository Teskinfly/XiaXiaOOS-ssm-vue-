---
title: ReadMe
date: 2020-10-08 21:27:30
tags:
	-项目介绍

---

## 虾虾订餐系统

#### 1. 项目介绍

虾虾订餐系统是一个前后端分离的网上订餐系统，项目采用SSM+Vue开发。

#### 2. 项目技术栈

###### 2.1 后端技术栈

1. Spring SpringMVC
2. Mybatis
3. MySQL
4. Redis
5. RabbitMQ

###### 2. 2 前端技术栈

1. Vue
2. ElementUI
3. axios     

#### 3. 项目效果图
###### 3.1 用户端

![用户端示意图](https://upload-images.jianshu.io/upload_images/21680686-9c9e4bd0f698f98e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

###### 3.2 管理员端

![管理员端示意图](https://upload-images.jianshu.io/upload_images/21680686-137839ed81a95bb2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 4. 后端部署

###### 4.1 项目克隆到本地，idea打开XiaxiaOOS_Rear

###### 4.2 数据库数据导入

4.2.3 创建数据库roos_db

4.2.4 运行roos_db.sql

4.2.5 打开```resources/db/db.properties```，修改数据库信息

###### 4.3 准备好redis

4.3.1 打开```resources/redis.proerties```,修改redis信息

###### 4.4 准备好rabbitmq

4.4.1  打开```resources/rabbitmq/rabbitmq.proerties```,修改redis信息

4.4.2 打开```resources/email/email.properties```, 换成自己邮箱的信息

###### 4.5 启动Tomcat，便ok

#### 5. 前端启动, 因为包括用户端与管理员端，需要单独启动

###### 5.1 cmd进入```XiaxiaOOS_Front\xiaxiaoos_front_admin```(管理员端为例)

###### 5.2 运行```npm install```

###### 5.3 运行```npm run serve```

#### 6. [项目地址](https://github.com/Teskinfly/XiaXiaOOS-ssm-vue-)





