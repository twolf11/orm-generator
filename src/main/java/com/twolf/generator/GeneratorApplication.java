package com.twolf.generator;

import com.twolf.common.orm.service.BaseService;
import com.twolf.common.orm.service.impl.BaseServiceImpl;
import com.twolf.generator.model.DatabaseInfo;
import com.twolf.generator.model.GenerateInfo;
import com.twolf.generator.model.TemplateInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成器main方法
 * @Author twolf
 * @Date 2021/04/13 09:00
 */
public class GeneratorApplication {

    public static void main(String[] args) {
        DatabaseInfo databaseInfo = DatabaseInfo.builder().ip("192.168.1.20")
                .port("3306")
                .database("wd-game-auth")
                .username("root")
                .password("root")
                .build();
        GenerateInfo generateInfo = com.twolf.generator.model.GenerateInfo.builder()
                .databaseInfo(databaseInfo)
                .templateInfo(TemplateInfo.baseServiceGrInstance())
                .author("twolf")
                .packagePath("com.twolf.generator.data")
                .moduleName("plus")
                .build();
        generateInfo.setOpenValidGroup(true);
        Map<String,String> map = new HashMap<>();
        map.put("add","com.twolf.generator.test.Add");
        map.put("update","com.twolf.generator.test.Update");
        generateInfo.setGroupImportPkgs(map);
        Map<String,String> validClass = new HashMap<>();
        validClass.put("add","Add");
        validClass.put("update","Update");
        generateInfo.setGroupImportClass(validClass);

        List<String> requestIgnoreFields = List.of("id","add_time");
        generateInfo.setRequestIgnoreFields(requestIgnoreFields);
        generateRuoyi(generateInfo, "accounts");
    }

    private static void defaultGenerate(GenerateInfo generateInfo, String... tableNames) {
        generateInfo.setTemplateInfo(TemplateInfo.defaultInstance());
        GeneratorUtil.generate(generateInfo, tableNames);
    }

    private static void generateGr(GenerateInfo generateInfo, String... tableNames) {
        generateInfo.setTemplateInfo(TemplateInfo.baseServiceGrInstance());
        generateInfo.setServiceSuperClass(BaseService.class);
        generateInfo.setServiceImplSuperClass(BaseServiceImpl.class);
        GeneratorUtil.generate(generateInfo, tableNames);
    }

    private static void generateRuoyi(GenerateInfo generateInfo, String... tableNames) {
        generateInfo.setTemplateInfo(TemplateInfo.ruoyiInstance());
        GeneratorUtil.generate(generateInfo, tableNames);
    }
}
