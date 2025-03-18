package com.twolf.generator.model;

import lombok.Data;

/**
 * 模板常量
 * @Author twolf
 * @Date 2025/3/18
 */
@Data
public class TemplateInfo {

    /** 实体模板地址 */
    private String entityTemplate = "/templates/model/entity.java";

    /** mapper模板地址 */
    private String mapperTemplate = "/templates/mapper/mapper.java";

    /** mapperXml模板地址 */
    private String mapperXmlTemplate = "/templates/mapper/mapper.xml";

    /** service模板地址 */
    private String serviceTemplate = "/templates/service/service.java";

    /** serviceImpl模板地址 */
    private String serviceImplTemplate = "/templates/service/serviceImpl.java";

    /** controller模板地址 */
    private String controllerTemplate = "/templates/controller/controller.java";

    private TemplateInfo() {
    }

    public static TemplateInfo defaultInstance() {
        return new TemplateInfo();
    }

    public static TemplateInfo baseServiceInstance() {
        TemplateInfo templateInfo = new TemplateInfo();
        templateInfo.setServiceImplTemplate("/templates/service/baseServiceImpl.java");
        return templateInfo;
    }

    public static TemplateInfo baseServiceGrInstance() {
        TemplateInfo templateInfo = new TemplateInfo();
        templateInfo.setServiceImplTemplate("/templates/service/baseServiceImpl.java");
        templateInfo.setControllerTemplate("/templates/controller/grController.java");
        return templateInfo;
    }


    public static TemplateInfo ruoyiInstance() {
        TemplateInfo templateInfo = new TemplateInfo();
        templateInfo.setServiceTemplate("/templates/service/ruoyiService.java");
        templateInfo.setServiceImplTemplate("/templates/service/ruoyiServiceImpl.java");
        templateInfo.setControllerTemplate("/templates/controller/ruoyiController.java");
        return templateInfo;
    }

}
