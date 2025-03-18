package ${package.Convert};

import ${package.Entity}.${entity};
import ${package.Request}.${entity}Request;
import ${package.Response}.${entity}Response;
<#list convertImportPkg as pkg>
import ${pkg};
</#list>

/**
 * ${table.comment}转换
 * @Author ${author}
 * @Date ${date}
 */
 @Mapper
public interface ${entity}Convert {

    ${entity}Convert INSTANCE = Mappers.getMapper(${entity}Convert.class);

    /**
     * request to entity
     * @param request request
     * @author ${author}
     * @date ${date}
     **/
    ${entity} requestToEntity(${entity}Request request);

    /**
     * entity to response
     * @param entity entity
     * @author ${author}
     * @date ${date}
     **/
    ${entity}Response entityToResponse(${entity} entity);

}
