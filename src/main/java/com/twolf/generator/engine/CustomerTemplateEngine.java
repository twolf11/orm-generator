package com.twolf.generator.engine;

import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.model.AnnotationAttributes;
import com.baomidou.mybatisplus.generator.model.ClassAnnotationAttributes;

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
        setValidData(tableInfo, objectMap);
        setEntityAnnotations(objectMap);
        super.outputCustomFile(customFiles, tableInfo, objectMap);
    }

    /**
     * 设置请求参数的校验
     * @param tableInfo 表数据
     * @param objectMap 参数
     * @author twolf
     * @date 2025/3/26 13:27
     **/
    private void setValidData(TableInfo tableInfo, Map<String, Object> objectMap) {
        Set<String> validationImportPkg = new HashSet<>();
        boolean openValidGroup = Optional.ofNullable(objectMap.get("openValidGroup")).map(Boolean.class::cast).orElse(false);
        Map<String, String> validMap = Optional.ofNullable(objectMap.get("groupImportClass")).map(Map.class::cast).orElse(new HashMap<String, String>());

        tableInfo.getFields().stream().peek(tableField -> {
            List<AnnotationAttributes> requestAnnotationAttributes = new ArrayList<>();
            Iterator<AnnotationAttributes> iterator = tableField.getAnnotationAttributesList().iterator();
            while (iterator.hasNext()) {
                AnnotationAttributes annotationAttribute = iterator.next();
                String displayName = annotationAttribute.getDisplayName();
                if (displayName.contains("@Schema")) {
                    iterator.remove();
                    requestAnnotationAttributes.add(annotationAttribute);
                } else if (!displayName.contains("@Table") && !displayName.contains("@Version")) {
                    requestAnnotationAttributes.add(annotationAttribute);
                }
            }
            Map<String, Object> customMap = Optional.ofNullable(tableField.getCustomMap()).orElse(new HashMap<>());
            customMap.put("requestAnnotationAttributes", requestAnnotationAttributes);
            tableField.setCustomMap(customMap);

            //设置主键参数
            if (tableField.isKeyFlag()) {
                if (tableField.getColumnType().getPkg() != null) {
                    objectMap.put("privateKeyPackage", tableField.getColumnType().getPkg());
                }
                objectMap.put("privateKeyType", tableField.getColumnType().getType());
            }
        }).filter(tableField -> !tableField.getMetaInfo().isNullable()).forEach(tableField -> {
            String annotationType;
            if (tableField.getColumnType() == STRING) {
                validationImportPkg.add("jakarta.validation.constraints.NotBlank");
                annotationType = "@NotBlank";
            } else {
                validationImportPkg.add("jakarta.validation.constraints.NotNull");
                annotationType = "@NotNull";
            }
            String validStr = "";
            if (openValidGroup) {
                if (tableField.isKeyFlag()) {
                    validStr = "groups = " + validMap.get("update") + ".class, ";
                } else {
                    validStr = "groups = " + validMap.get("add") + ".class, ";
                }
            }
            String annotation = "(" + validStr + "message = \"" + tableField.getComment() + "不能为空" + "\")";
            Map<String, Object> customMap = Optional.ofNullable(tableField.getCustomMap()).orElse(new HashMap<>());
            customMap.put("validationAnnotation", annotationType + annotation);
            tableField.setCustomMap(customMap);
        });
        objectMap.put("validationImportPkg", validationImportPkg);
    }

    /**
     * 设置request的响应参数
     * @param objectMap 全局参数
     * @author twolf
     * @date 2025/3/26 10:51
     **/
    private void setEntityAnnotations(Map<String, Object> objectMap) {
        List<ClassAnnotationAttributes> entityClassAnnotations = (List<ClassAnnotationAttributes>) objectMap.get("entityClassAnnotations");
        List<ClassAnnotationAttributes> requestEntityClassAnnotations = entityClassAnnotations.stream().filter(classAnnotationAttribute -> {
            String displayName = classAnnotationAttribute.getDisplayName();
            if (displayName != null && displayName.contains("@Schema")) {
                displayName = displayName.replaceAll("name\\s*=\\s*\"[^\"]*\"\\s*, ?", "");
                classAnnotationAttribute.setDisplayName(displayName);
            }
            return !classAnnotationAttribute.getDisplayName().contains("@Table");
        }).toList();
        entityClassAnnotations = entityClassAnnotations.stream().filter(classAnnotationAttribute -> !classAnnotationAttribute.getDisplayName().contains("@Schema")).toList();

        objectMap.put("entityClassAnnotations", entityClassAnnotations);
        objectMap.put("requestEntityClassAnnotations", requestEntityClassAnnotations);
    }
}
