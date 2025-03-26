package ${package.Request};

<#list importEntityPackages as pkg>
    <#if !pkg?contains("mybatisplus")>
import ${pkg};
    </#if>
</#list>
<#if groupImportPkgs??>
    <#list groupImportPkgs?keys as key>
import ${groupImportPkgs[key]};
    </#list>
</#if>
<#list validationImportPkg as pkg>
import ${pkg};
</#list>
<#if superRequestClassImport??>
import ${superRequestClassImport};
</#if>

/**
 * ${table.comment}
 * @Author ${author}
 * @Date ${date}
 */
<#list requestEntityClassAnnotations as an>
${an.displayName}
</#list>
<#if superRequestClass??>
public class ${entity}Request extends ${superRequestClass} {
<#elseif entitySerialVersionUID>
public class ${entity}Request implements Serializable {
<#else>
public class ${entity}Request {
</#if>
<#if entitySerialVersionUID>

    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if (!requestIgnoreFields?has_content || !requestIgnoreFields?seq_contains(field.name)) && field.name != logicDeleteFieldName>
        <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
        </#if>

        <#if field.comment!?length gt 0>
            <#if entityFieldUseJavaDoc>
    /** ${field.comment} */
            </#if>
        </#if>

        <#if field.customMap??>
            <#if field.customMap.requestAnnotationAttributes??>
                <#list field.customMap.requestAnnotationAttributes as an>
    ${an.displayName}
                </#list>
            </#if>
            <#if field.customMap.validationAnnotation??>
    ${field.customMap.validationAnnotation}
            </#if>
        </#if>
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->
<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>

    public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
    }

    <#if chainModel>
    public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    <#else>
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
    </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if chainModel>
        return this;
        </#if>
    }
    </#list>
</#if>
<#if entityColumnConstant>
    <#list table.fields as field>

    public static final String ${field.name?upper_case} = "${field.name}";
    </#list>
</#if>
<#if activeRecord>

    @Override
    public Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }
</#if>
<#if !entityLombokModel && entityToString>

    @Override
    public String toString() {
        return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
            "${field.propertyName} = " + ${field.propertyName} +
        <#else>
            ", ${field.propertyName} = " + ${field.propertyName} +
        </#if>
    </#list>
        "}";
    }
</#if>
}
