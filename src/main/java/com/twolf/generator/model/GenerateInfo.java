package com.twolf.generator.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 生成类配置
 * @Author twolf
 * @Date 2025/3/18
 */
@Data
@Builder(toBuilder = true)
public class GenerateInfo {

    /** 数据库配置 */
    private DatabaseInfo databaseInfo;

    /** 模板配置 */
    private TemplateInfo templateInfo;

    /**
     * 作者
     */
    private String author;

    /**
     * 包路径
     */
    private String packagePath;

    /**
     * 模块名称
     */
    private String moduleName;

    /** 开启分组校验 */
    private boolean openValidGroup;

    /** 分组校验的包 */
    private Map<String,String> groupImportPkgs;

    /** 分组校验的类名 */
    private Map<String,String> groupImportClass;

    /**
     * 排除的表前缀
     */
    private String[] excludeTablePrefix;

    /**
     * entity继承的父类
     */
    private Class<?> entitySuperClass;

    /**
     * entity继承的父类的字段
     */
    private String[] superEntityColumns;

    /**
     * mapper继承的父类
     */
    private Class<?> mapperSuperClass;

    /**
     * service继承的父类
     */
    private Class<?> serviceSuperClass;

    /**
     * serviceImpl继承的父类
     */
    private Class<?> serviceImplSuperClass;

    /**
     * controller继承的父类
     */
    private Class<?> controllerSuperClass;

    /**
     * 模板传递的参数
     */
    private Map<String, Object> paramMap;

    /**
     * request忽略的字段
     */
    private List<String> requestIgnoreFields;
}
