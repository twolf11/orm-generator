package com.twolf.generator.data.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.twolf.generator.data.service.plus.AccountsService;
import com.twolf.generator.data.model.request.plus.AccountsRequest;
import com.twolf.generator.data.model.request.plus.AccountsQueryRequest;
import com.twolf.generator.data.model.response.plus.AccountsResponse;
import com.twolf.generator.data.model.convert.plus.AccountsConvert;
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
* 相关api接口
* @Author twolf
* @Date 2025-03-18
*/
@RestController
@RequestMapping("/api/accounts/v1")
@Tag(name = "接口")
@AllArgsConstructor
@Slf4j
public class AccountsController extends BaseController {

    /** service */
    private final AccountsService accountsService;

    /**
     * 添加
     * @param request 参数
     * @author twolf
     * @date 2025-03-18
     **/
    @PreAuthorize("@ss.hasPermi('plus:accounts:add')")
    @Log(title = "添加", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @Operation(summary = "添加")
    public Result<Void> add(@RequestBody AccountsRequest request) {
        accountsService.add(request);
        return Result.success();
    }

    /**
     * 修改
     * @param request 参数
     * @author twolf
     * @date 2025-03-18
     **/
    @PreAuthorize("@ss.hasPermi('plus:accounts:edit')")
    @Log(title = "添加", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    @Operation(summary = "修改")
    public Result<Void> update(@RequestBody AccountsRequest request) {
        accountsService.update(request);
        return Result.success();
    }

    /**
     * 删除
     * @param privateKey 主键
     * @author twolf
     * @date 2025-03-18
     **/
    @PreAuthorize("@ss.hasPermi('plus:accounts:delete')")
    @Log(title = "添加", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{privateKey}")
    @Operation(summary = "删除")
    public Result<Boolean> delete(@PathVariable Integer privateKey) {
        return Result.success(accountsService.removeById(privateKey));
    }

    /**
     * 获取详情
     * @param privateKey 主键
     * @author twolf
     * @date 2025-03-18
     **/
    @PreAuthorize("@ss.hasPermi('plus:accounts:list')")
    @GetMapping("/detail/{privateKey}")
    @Operation(summary = "获取详情")
    public Result<AccountsResponse> detail(@PathVariable Integer privateKey) {
        return Result.success(Optional.ofNullable(accountsService.getById(privateKey))
                .map(AccountsConvert.INSTANCE::entityToResponse)
                .orElse(null));
    }

    /**
     * 列表查询
     * @param request 参数
     * @author twolf
     * @date 2025-03-18
     **/
    @PreAuthorize("@ss.hasPermi('plus:accounts:list')")
    @PostMapping("/list")
    @Operation(summary = "列表查询")
    public Result<List<AccountsResponse>> list(@RequestBody AccountsQueryRequest request) {
        return Result.success(accountsService.queryList(request).stream()
                .map(AccountsConvert.INSTANCE::entityToResponse)
                .toList());
    }

    /**
     * 分页查询
     * @param request 参数
     * @author twolf
     * @date 2025-03-18
     **/
    @PreAuthorize("@ss.hasPermi('plus:accounts:list')")
    @PostMapping("/page")
    @Operation(summary = "分页查询")
    public TableDataInfo queryPage(@RequestBody AccountsQueryRequest request) {
        startPage();
        return getDataTable(accountsService.queryList(request).stream()
                .map(AccountsConvert.INSTANCE::entityToResponse).toList());
    }
}