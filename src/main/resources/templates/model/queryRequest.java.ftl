package ${package.QueryRequest};

<#list importEntityPackages as pkg>
    <#if !pkg?contains("mybatisplus")>
import ${pkg};
    </#if>
</#list>
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
<#list entityClassAnnotations as an>
    <#if !an.displayName?contains("@Table")>
${an.displayName}
    </#if>
</#list>
<#if superRequestClass??>
public class ${entity}QueryRequest extends ${superRequestClass} {
<#elseif entitySerialVersionUID>
public class ${entity}QueryRequest implements Serializable {
<#else>
public class ${entity}QueryRequest {
</#if>
<#if entitySerialVersionUID>

    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
        <#if entityFieldUseJavaDoc>
    /** ${field.comment} */
        </#if>
    </#if>
    <#list field.annotationAttributesList as an>
        <#if !an.displayName?contains("@Table")>
    ${an.displayName}
        </#if>
    </#list>
    private ${field.propertyType} ${field.propertyName};
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
