package ${package.ServiceImpl};

import ${superServiceImplClassPackage};
import ${package.Entity}.${entity};
import ${package.Request}.${entity}Request;
import ${package.QueryRequest}.${entity}QueryRequest;
import ${package.Convert}.${entity}Convert;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * ${table.comment}的service接口实现
 * @Author ${author}
 * @Date ${date}
 */
@Service
@AllArgsConstructor
@Slf4j
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName}{

    @Override
    public void add(${entity}Request request) {
        ${entity} ${table.entityPath} = ${entity}Convert.INSTANCE.requestToEntity(request);
        save(${table.entityPath});
    }

    @Override
    public void update(${entity}Request request) {
        ${entity} ${table.entityPath} = ${entity}Convert.INSTANCE.requestToEntity(request);
        updateById(${table.entityPath});
    }

    @Override
    public List<${entity}> queryList(${entity}QueryRequest request) {
        return list(buildQueryWrapper(request));
    }

    /**
     * 构建查询参数信息
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    private LambdaQueryWrapper<${entity}> buildQueryWrapper(${entity}QueryRequest request) {
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();
        if (request != null) {
            return queryWrapper;
        }
        queryWrapper
        <#list table.fields as field>
            <#if field.propertyType == "boolean">
                    <#assign getprefix="is"/>
                <#else>
                    <#assign getprefix="get"/>
                </#if>
            <#if field.columnType.type == "String" >
        .eq(${entity}::${getprefix}${field.capitalName},request.${getprefix}${field.capitalName}())
            </#if>
        </#list>
        ;
        return queryWrapper;
    }

}
