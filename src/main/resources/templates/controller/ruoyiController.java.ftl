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
import com.twolf.common.core.data.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.byd.rcs.common.annotation.Log;
import com.byd.rcs.common.core.controller.BaseController;
import com.byd.rcs.common.core.page.TableDataInfo;
import com.byd.rcs.common.enums.BusinessType;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class ${table.controllerName} extends BaseController {

    /** ${table.comment}service */
    private final ${table.serviceName} ${table.entityPath}Service;

    /**
     * 添加
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    @PreAuthorize("@ss.hasPermi('${moduleName}:${table.entityPath}:add')")
    @Log(title = "添加${table.comment}", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    <#if springdoc>
    @Operation(summary = "添加")
    </#if>
    public Result<Void> add(@RequestBody ${entity}Request request) {
        ${table.entityPath}Service.add(request);
        return Result.success();
    }

    /**
     * 修改
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    @PreAuthorize("@ss.hasPermi('${moduleName}:${table.entityPath}:edit')")
    @Log(title = "添加${table.comment}", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    <#if springdoc>
    @Operation(summary = "修改")
    </#if>
    public Result<Void> update(@RequestBody ${entity}Request request) {
        ${table.entityPath}Service.update(request);
        return Result.success();
    }

    /**
     * 删除
     * @param privateKey 主键
     * @author ${author}
     * @date ${date}
     **/
    @PreAuthorize("@ss.hasPermi('${moduleName}:${table.entityPath}:delete')")
    @Log(title = "添加${table.comment}", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{privateKey}")
    <#if springdoc>
    @Operation(summary = "删除")
    </#if>
    public Result<Boolean> delete(@PathVariable ${privateKeyType} privateKey) {
        return Result.success(${table.entityPath}Service.removeById(privateKey));
    }

    /**
     * 获取详情
     * @param privateKey 主键
     * @author ${author}
     * @date ${date}
     **/
    @PreAuthorize("@ss.hasPermi('${moduleName}:${table.entityPath}:list')")
    @GetMapping("/detail/{privateKey}")
    <#if springdoc>
    @Operation(summary = "获取详情")
    </#if>
    public Result<${entity}Response> detail(@PathVariable ${privateKeyType} privateKey) {
        return Result.success(Optional.ofNullable(${table.entityPath}Service.getById(privateKey))
                .map(${entity}Convert.INSTANCE::entityToResponse)
                .orElse(null));
    }

    /**
     * 列表查询
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    @PreAuthorize("@ss.hasPermi('${moduleName}:${table.entityPath}:list')")
    @PostMapping("/list")
    <#if springdoc>
    @Operation(summary = "列表查询")
    </#if>
    public Result<List<${entity}Response>> list(@RequestBody ${entity}QueryRequest request) {
        return Result.success(${table.entityPath}Service.queryList(request).stream()
                .map(${entity}Convert.INSTANCE::entityToResponse)
                .toList());
    }

    /**
     * 分页查询
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    @PreAuthorize("@ss.hasPermi('${moduleName}:${table.entityPath}:list')")
    @PostMapping("/page")
    <#if springdoc>
    @Operation(summary = "分页查询")
    </#if>
    public TableDataInfo queryPage(@RequestBody ${entity}QueryRequest request) {
        startPage();
        return getDataTable(${table.entityPath}Service.queryList(request).stream()
                .map(${entity}Convert.INSTANCE::entityToResponse).toList());
    }
}