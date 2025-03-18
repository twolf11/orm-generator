package com.twolf.generator.data.model.convert.plus;

import com.twolf.generator.data.model.domain.plus.Accounts;
import com.twolf.generator.data.model.request.plus.AccountsRequest;
import com.twolf.generator.data.model.response.plus.AccountsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 转换
 * @Author twolf
 * @Date 2025-03-18
 */
 @Mapper
public interface AccountsConvert {

    AccountsConvert INSTANCE = Mappers.getMapper(AccountsConvert.class);

    /**
     * request to entity
     * @param request request
     * @author twolf
     * @date 2025-03-18
     **/
    Accounts requestToEntity(AccountsRequest request);

    /**
     * entity to response
     * @param entity entity
     * @author twolf
     * @date 2025-03-18
     **/
    AccountsResponse entityToResponse(Accounts entity);

}
