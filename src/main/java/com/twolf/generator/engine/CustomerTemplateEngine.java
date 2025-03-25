package com.twolf.generator.engine;

import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.STRING;

/**
 * 自定义模板
 * @Author twolf
 * @Date 2024/11/29
 */
public class CustomerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(List<CustomFile> customFiles, TableInfo tableInfo, Map<String, Object> objectMap) {
        List<TableField> tableFields = tableInfo.getFields().stream().filter(tableField -> {
            if (tableField.isKeyFlag()) {
                if (tableField.getColumnType().getPkg() != null) {
                    objectMap.put("privateKeyPackage", tableField.getColumnType().getPkg());
                }
                objectMap.put("privateKeyType", tableField.getColumnType().getType());
            }
            return !tableField.getMetaInfo().isNullable();
        }).toList();
        Set<String> validationImportPkg = new HashSet<>();
        boolean openValidGroup = Optional.ofNullable(objectMap.get("openValidGroup")).map(Boolean.class::cast).orElse(false);
        Map<String, String> validMap = Optional.ofNullable(objectMap.get("groupImportClass")).map(Map.class::cast).orElse(new HashMap<String, String>());
        tableFields.forEach(tableField -> {
            String annotationType;
            if (tableField.getColumnType() == STRING) {
//                validationImportPkg.add("javax.validation.constraints.NotBlank");
                validationImportPkg.add("jakarta.validation.constraints.NotBlank");
                annotationType = "@NotBlank";
            } else {
//                validationImportPkg.add("javax.validation.constraints.NotNull");
                validationImportPkg.add("jakarta.validation.constraints.NotNull");
                annotationType = "@NotNull";
            }
            String validStr = "";
            if(openValidGroup){
                if(tableField.isKeyFlag()){
                    validStr = "groups = "+validMap.get("update")+".class, ";
                }else {
                    validStr = "groups = "+validMap.get("add")+".class, ";
                }
            }
            String annotation = "("+validStr+"message = \"" + tableField.getComment() + "不能为空" + "\")";
            Map<String, Object> customMap = new HashMap<>();
            customMap.put("validationAnnotation", annotationType + annotation);
            tableField.setCustomMap(customMap);
        });
        objectMap.put("validationImportPkg", validationImportPkg);
        super.outputCustomFile(customFiles, tableInfo, objectMap);
    }
}
