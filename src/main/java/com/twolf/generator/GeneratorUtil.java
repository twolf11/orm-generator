package com.twolf.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.builder.*;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.twolf.generator.engine.CustomerTemplateEngine;
import com.twolf.generator.model.GenerateInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成器工具
 * @Author twolf
 * @Date 2021/04/13 09:00
 */
public class GeneratorUtil {

    /**
     * 生成代码
     * @param generateInfo 参数
     * @param tableNames   表名
     * @author twolf
     * @date 2022/1/24 18:08
     **/
    public static void generate(GenerateInfo generateInfo, String... tableNames) {
        FastAutoGenerator.create(generateInfo.getDatabaseInfo().getJdbcUrl(), generateInfo.getDatabaseInfo().getUsername(), generateInfo.getDatabaseInfo().getPassword())
                .globalConfig(builder -> {
                    builder.author(generateInfo.getAuthor()) // 设置作者
                            .outputDir(System.getProperty("user.dir") + "/src/main/java/"); // 指定输出目录
                    builder.enableSpringdoc()
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent(generateInfo.getPackagePath()) // 设置父包名
                            .entity("model.domain." + generateInfo.getModuleName())
                            .mapper("mapper." + generateInfo.getModuleName())
                            .service("service." + generateInfo.getModuleName())
                            .serviceImpl("service." + generateInfo.getModuleName() + ".impl")
                            .xml("mapper." + generateInfo.getModuleName() + ".xml");
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude(tableNames);
                    if(generateInfo.getExcludeTablePrefix() != null && generateInfo.getExcludeTablePrefix().length > 0){
                        builder.addTablePrefix(generateInfo.getExcludeTablePrefix());
                    }
                    //实体类配置
                    entityConfig(generateInfo, builder.entityBuilder());
                    //mapper配置
                    mapperConfig(generateInfo, builder.mapperBuilder());
                    //service
                    serviceConfig(generateInfo, builder.serviceBuilder());
                    //controller
                    controllerConfig(generateInfo, builder.controllerBuilder());
                    builder.build();
                })
                //使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new CustomerTemplateEngine())
                .injectionConfig(builder -> dtoVoConfig(generateInfo, builder))
                .execute();
    }

    /**
     * 实体配置
     * @param builder builder
     * @author twolf
     * @date 2025/3/14 13:31
     **/
    private static void entityConfig(GenerateInfo generateInfo, Entity.Builder builder) {
        builder.javaTemplate(generateInfo.getTemplateInfo().getEntityTemplate());
        if (generateInfo.getEntitySuperClass() != null) {
            builder.superClass(generateInfo.getEntitySuperClass());
        }
        if (generateInfo.getSuperEntityColumns() != null && generateInfo.getSuperEntityColumns().length > 0) {
            builder.addSuperEntityColumns(generateInfo.getSuperEntityColumns());
        }
        builder.disableSerialVersionUID()
                .enableChainModel()
                .enableLombok()
                .enableRemoveIsPrefix()
                .enableFileOverride()
                .versionColumnName("reversion")
                .logicDeleteColumnName("is_delete")
                .addTableFills(new Column("create_time", FieldFill.INSERT), new Column("update_time", FieldFill.UPDATE));
    }

    /**
     * 实体配置
     * @param builder builder
     * @author twolf
     * @date 2025/3/14 13:31
     **/
    private static void dtoVoConfig(GenerateInfo generateInfo, InjectionConfig.Builder builder) {
        List<String> convertImport = new ArrayList<>();
        convertImport.add("org.mapstruct.Mapper");
        convertImport.add("org.mapstruct.factory.Mappers");
        convertImport.add("org.mapstruct.ReportingPolicy;");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("superRequestClassImport", null);
        paramMap.put("superRequestClass", null);
        paramMap.put("superResponseClassImport", null);
        paramMap.put("superResponseClass", null);
        paramMap.put("convertImportPkg", convertImport);
        paramMap.put("moduleName", generateInfo.getModuleName());
        paramMap.put("openValidGroup", generateInfo.isOpenValidGroup() );
        paramMap.put("groupImportPkgs", generateInfo.getGroupImportPkgs());
        paramMap.put("groupImportClass", generateInfo.getGroupImportClass());
        paramMap.put("requestIgnoreFields", generateInfo.getRequestIgnoreFields());
        builder.customMap(paramMap);
        builder.customFile(new CustomFile.Builder()
                .enableFileOverride()
                //文件名称
                .fileName("Request.java")
                .templatePath("/templates/model/request.java.ftl")
                .packageName("model.request." + generateInfo.getModuleName())
                .build());
        builder.customFile(new CustomFile.Builder()
                .enableFileOverride()
                //文件名称
                .fileName("QueryRequest.java")
                .templatePath("/templates/model/queryRequest.java.ftl")
                .packageName("model.request." + generateInfo.getModuleName())
                .build());
        builder.customFile(new CustomFile.Builder()
                .enableFileOverride()
                //文件名称
                .fileName("Response.java")
                .templatePath("/templates/model/response.java.ftl")
                .packageName("model.response." + generateInfo.getModuleName())
                .build());
        builder.customFile(new CustomFile.Builder()
                .enableFileOverride()
                //文件名称
                .fileName("Convert.java")
                .templatePath("/templates/convert/convert.java.ftl")
                .packageName("model.convert." + generateInfo.getModuleName())
                .build());
    }

    /**
     * mapper配置
     * @param builder builder
     * @author twolf
     * @date 2025/3/14 13:31
     **/
    private static void mapperConfig(GenerateInfo generateInfo, Mapper.Builder builder) {
        builder.mapperTemplate(generateInfo.getTemplateInfo().getMapperTemplate())
                .mapperXmlTemplate(generateInfo.getTemplateInfo().getMapperXmlTemplate());
        if (generateInfo.getMapperSuperClass() != null) {
            builder.superClass(generateInfo.getMapperSuperClass());
        }
        builder.enableFileOverride()
                .enableBaseResultMap()
                .enableBaseColumnList()
                .formatMapperFileName("%sMapper")
                .formatXmlFileName("%sMapper");
    }

    /**
     * service配置
     * @param builder builder
     * @author twolf
     * @date 2025/3/14 13:31
     **/
    private static void serviceConfig(GenerateInfo generateInfo, Service.Builder builder) {
        builder.serviceTemplate(generateInfo.getTemplateInfo().getServiceTemplate())
                .serviceImplTemplate(generateInfo.getTemplateInfo().getServiceImplTemplate());
        if (generateInfo.getServiceSuperClass() != null) {
            builder.superServiceClass(generateInfo.getServiceSuperClass());
        }
        if (generateInfo.getServiceImplSuperClass() != null) {
            builder.superServiceImplClass(generateInfo.getServiceImplSuperClass());
        }
        builder.serviceBuilder()
                .enableFileOverride()
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImpl");
    }

    /**
     * controller配置
     * @param builder builder
     * @author twolf
     * @date 2025/3/14 13:31
     **/
    private static void controllerConfig(GenerateInfo generateInfo, Controller.Builder builder) {
        builder.template(generateInfo.getTemplateInfo().getControllerTemplate());
        if (generateInfo.getControllerSuperClass() != null) {
            builder.superClass(generateInfo.getControllerSuperClass());
        }
        builder.enableFileOverride()
                .enableRestStyle()
                .formatFileName("%sController");
    }

}
