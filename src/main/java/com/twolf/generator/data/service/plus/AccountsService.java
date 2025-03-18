package com.twolf.generator.data.service.plus;

import com.baomidou.mybatisplus.extension.service.IService;
import com.twolf.generator.data.model.domain.plus.Accounts;
import com.twolf.generator.data.model.request.plus.AccountsRequest;
import com.twolf.generator.data.model.request.plus.AccountsQueryRequest;
import java.util.List;

/**
 * 的service接口
 * @Author twolf
 * @Date 2025-03-18
 */
public interface AccountsService extends IService<Accounts> {

    /**
     * 添加
     * @param request 参数
     * @author twolf
     * @date 2025-03-18
     **/
    void add(AccountsRequest request);

    /**
     * 修改
     * @param request 参数
     * @author twolf
     * @date 2025-03-18
     **/
    void update(AccountsRequest request);

    /**
     * 获取列表
     * @param request 参数
     * @author twolf
     * @date 2025-03-18
     **/
    List<Accounts> queryList(AccountsQueryRequest request);

}
