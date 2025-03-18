package com.twolf.generator.data.service.plus.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.twolf.generator.data.model.domain.plus.Accounts;
import com.twolf.generator.data.model.request.plus.AccountsRequest;
import com.twolf.generator.data.model.request.plus.AccountsQueryRequest;
import com.twolf.generator.data.model.convert.plus.AccountsConvert;
import com.twolf.generator.data.mapper.plus.AccountsMapper;
import com.twolf.generator.data.service.plus.AccountsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * 的service接口实现
 * @Author twolf
 * @Date 2025-03-18
 */
@Service
@AllArgsConstructor
@Slf4j
public class AccountsServiceImpl extends ServiceImpl<AccountsMapper, Accounts> implements AccountsService{

    @Override
    public void add(AccountsRequest request) {
        Accounts accounts = AccountsConvert.INSTANCE.requestToEntity(request);
        save(accounts);
    }

    @Override
    public void update(AccountsRequest request) {
        Accounts accounts = AccountsConvert.INSTANCE.requestToEntity(request);
        updateById(accounts);
    }

    @Override
    public List<Accounts> queryList(AccountsQueryRequest request) {
        return list(buildQueryWrapper(request));
    }

    /**
     * 构建查询参数信息
     * @param request 参数
     * @author twolf
     * @date 2025-03-18
     **/
    private LambdaQueryWrapper<Accounts> buildQueryWrapper(AccountsQueryRequest request) {
        LambdaQueryWrapper<Accounts> queryWrapper = new LambdaQueryWrapper<>();
        if (request != null) {
            return queryWrapper;
        }
        queryWrapper
        .eq(Accounts::getName,request.getName())
        .eq(Accounts::getKeyword,request.getKeyword())
        .eq(Accounts::getPassword,request.getPassword())
        .eq(Accounts::getToken,request.getToken())
        .eq(Accounts::getRegisterCode,request.getRegisterCode())
        .eq(Accounts::getRegisterIp,request.getRegisterIp())
        .eq(Accounts::getLastLoginIp,request.getLastLoginIp())
        .eq(Accounts::getMac,request.getMac())
        .eq(Accounts::getLastLoginMac,request.getLastLoginMac())
        ;
        return queryWrapper;
    }

}
