# orm-generator

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

增加valid分组校验，通过GenerateInfo的参数配置，注意map的属性一定要一致，参考如下：
```
        generateInfo.setOpenValidGroup(true);
        Map<String,String> map = new HashMap<>();
        map.put("add","com.twolf.generator.test.Add");
        map.put("update","com.twolf.generator.test.Update");
        generateInfo.setGroupImportPkgs(map);
        Map<String,String> validClass = new HashMap<>();
        validClass.put("add","Add");
        validClass.put("update","Update");
        generateInfo.setGroupImportClass(validClass);
```
增加request实体忽略一些字段属性，参考如下：
```
        List<String> requestIgnoreFields = List.of("id","add_time");
        generateInfo.setRequestIgnoreFields(requestIgnoreFields);
```