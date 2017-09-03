# pingguopai
## 简介
   pingguopai是学习使用spring boot 如何结合使用spring mvc, spring security, jwt mybatis, druid数据源的项目，
一键代码生成，简化开发，专注业务实现。
## 新特性
- 添加model基类，保存更新记录更新时间
- 添加分页接口，重写分页查询基础类。
- mapper中添加PK泛型，便于适应现有项目架构
- 基于spring security的无状态jwt认证体系
- 内存缓存
## 待加特性
- 乐观锁   
## 技术选型&文档
- Spring Boot
- MyBatis（[查看官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）
- MyBatisb通用Mapper插件（[查看官方中文文档](https://mapperhelper.github.io/docs/)）
- MyBatis PageHelper分页插件（[查看官方中文文档](https://pagehelper.github.io/)）[后续会废除自己封装]
- Druid Spring Boot Starter（[查看官方中文文档](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter/)）
- jackson
- spring security
- 参考项目：https://github.com/lihengming/spring-boot-api-project-seed/
