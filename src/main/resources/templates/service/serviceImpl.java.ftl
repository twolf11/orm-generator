package ${package.ServiceImpl};

import ${superServiceImplClassPackage};
import ${package.Entity}.${entity};
import ${package.Request}.${entity}Request;
import ${package.QueryRequest}.${entity}QueryRequest;
import ${package.Convert}.${entity}Convert;
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import com.twolf.common.core.model.PageRequest;
import com.twolf.common.core.model.PageResponse;
import com.twolf.common.core.util.AssertUtil;
import com.twolf.common.core.util.Tools;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
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
        boolean result = save(${table.entityPath});
        AssertUtil.isTrue(!result, "新增失败");
    }

    @Override
    public void update(${entity}Request request) {
        ${entity} ${table.entityPath} = ${entity}Convert.INSTANCE.requestToEntity(request);
        boolean result = updateById(${table.entityPath});
        AssertUtil.isTrue(!result, "修改失败");
    }

    @Override
    public List<${entity}> queryList(${entity}QueryRequest request) {
        return list(buildQueryWrapper(request));
    }

    @Override
    public PageResponse<${entity}> queryPage(PageRequest<${entity}QueryRequest> pageRequest) {
        LambdaQueryWrapper<${entity}> queryWrapper = buildQueryWrapper(pageRequest.getParam());
        Page<${entity}> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        if (pageRequest.getOrderInfos() != null && !pageRequest.getOrderInfos().isEmpty()) {
            List<OrderItem> orderItems = pageRequest.getOrderInfos().stream().map(orderInfo -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setColumn(orderInfo.getColumn());
                orderItem.setAsc(orderInfo.isAsc());
                return orderItem;
            }).toList();
            page.setOrders(orderItems);
        }
        page(page, queryWrapper);
        return new PageResponse<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }

    /**
     * 构建查询参数信息
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    private LambdaQueryWrapper<${entity}> buildQueryWrapper(${entity}QueryRequest request) {
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();
        if (request == null) {
            return queryWrapper;
        }
        queryWrapper
        <#list table.fields as field>
            <#if !requestIgnoreFields?has_content || !requestIgnoreFields?seq_contains(field.name)>
                <#if field.propertyType == "boolean">
                        <#assign getprefix="is"/>
                    <#else>
                        <#assign getprefix="get"/>
                    </#if>
                <#if field.columnType.type == "String" >
                .eq(Tools.isNotEmpty(request.${getprefix}${field.capitalName}()),${entity}::${getprefix}${field.capitalName},request.${getprefix}${field.capitalName}())
                </#if>
            </#if>
        </#list>
        ;
        return queryWrapper;
    }

}
