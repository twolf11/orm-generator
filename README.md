# base-common

#### 介绍

基于脚手架项目base-common以及mybatisplus generator生成器改造的代码生成项目，基于Freemarker模板引擎生成代码

1. 支持生成基于base-common的代码生成
2. 支持生成基于graceful-response的代码生成
3. 支持生成基于若依mybatisplus版本框架的代码生成
4. 增加基于mapstruct的实体转换器生成
5. 增加基于request、response的POJO对象生成
6. 增加service、controller一系列增、 删、改、查等接口生成

#### 软件架构

基于脚手架项目base-common的软件架构

#### 使用说明

拉取项目base-common对应版本，在本地构建，如果有私有仓库则直接推送私有仓库。
直接运行GeneratorApplication.java文件的mian方法，选择需要支持的类型即可。