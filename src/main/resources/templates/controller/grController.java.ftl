package ${package.Controller};

<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
<#if superControllerClass??>
import ${superControllerClassPackage};
</#if>
<#if privateKeyPackage??>
import ${privateKeyPackage};
</#if>
<#if springdoc>
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
</#if>
import ${package.Service}.${table.serviceName};
import ${package.Request}.${entity}Request;
import ${package.QueryRequest}.${entity}QueryRequest;
import ${package.Response}.${entity}Response;
import ${package.Convert}.${entity}Convert;
import com.twolf.common.core.model.PageRequest;
import com.twolf.common.core.model.PageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;


/**
* ${table.comment}相关api接口
* @Author ${author}
* @Date ${date}
*/
@RestController
@RequestMapping("/api/${table.entityPath}/v1")
<#if springdoc>
@Tag(name = "${table.comment}接口")
</#if>
@AllArgsConstructor
@Slf4j
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {

</#if>
    /** ${table.comment}service */
    private final ${table.serviceName} ${table.entityPath}Service;

    /**
     * 添加
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    @PostMapping("/add")
    <#if springdoc>
    @Operation(summary = "添加")
    </#if>
    public void add(@RequestBody ${entity}Request request) {
        ${table.entityPath}Service.add(request);
    }

    /**
     * 修改
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    @PutMapping("/update")
    <#if springdoc>
    @Operation(summary = "修改")
    </#if>
    public void update(@RequestBody ${entity}Request request) {
        ${table.entityPath}Service.update(request);
    }

    /**
     * 删除
     * @param privateKey 主键
     * @author ${author}
     * @date ${date}
     **/
    @DeleteMapping("/delete/{privateKey}")
    <#if springdoc>
    @Operation(summary = "删除")
    </#if>
    public Boolean delete(@PathVariable ${privateKeyType} privateKey) {
        return ${table.entityPath}Service.removeById(privateKey);
    }

    /**
     * 获取详情
     * @param privateKey 主键
     * @author ${author}
     * @date ${date}
     **/
    @GetMapping("/detail/{privateKey}")
    <#if springdoc>
    @Operation(summary = "获取详情")
    </#if>
    public ${entity}Response detail(@PathVariable ${privateKeyType} privateKey) {
        return Optional.ofNullable(${table.entityPath}Service.getById(privateKey))
                .map(${entity}Convert.INSTANCE::entityToResponse)
                .orElse(null);
    }

    /**
     * 列表查询
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    @PostMapping("/list")
    <#if springdoc>
    @Operation(summary = "列表查询")
    </#if>
    public List<${entity}Response> list(@RequestBody ${entity}QueryRequest request) {
        return ${table.entityPath}Service.queryList(request).stream()
                .map(${entity}Convert.INSTANCE::entityToResponse)
                .toList();
    }

    /**
     * 分页查询
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    @PostMapping("/page")
    <#if springdoc>
    @Operation(summary = "分页查询")
    </#if>
    public PageResponse<${entity}Response> queryPage(@RequestBody PageRequest<${entity}QueryRequest> request) {
        return ${table.entityPath}Service.queryPage(request).convert(${entity}Convert.INSTANCE::entityToResponse);
    }
}