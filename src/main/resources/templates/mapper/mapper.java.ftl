package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
<#if generateKeyXml??>
import org.apache.ibatis.annotations.Param;
</#if>

/**
 * ${table.comment}的Mapper接口
 * @Author ${author}
 * @Date ${date}
 */
 <#if mapperAnnotationClass??>
 @${mapperAnnotationClass.simpleName}
 </#if>
 <#if kotlin>
 interface ${table.mapperName} : ${superMapperClass}<${entity}> {
 <#else>
 public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
 </#if>

 <#list mapperMethodList as m>
     /**
      * generate by ${m.indexName}
      *
     <#list m.tableFieldList as f>
      * @param ${f.propertyName} ${f.comment}
     </#list>
      */
     ${m.method}
 </#list>
}
