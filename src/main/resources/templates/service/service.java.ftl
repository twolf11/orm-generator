package ${package.Service};

import ${superServiceClassPackage};
import ${package.Entity}.${entity};
import ${package.Request}.${entity}Request;
import ${package.QueryRequest}.${entity}QueryRequest;
import com.twolf.common.core.model.PageRequest;
import com.twolf.common.core.model.PageResponse;
import java.util.List;

/**
 * ${table.comment}的service接口
 * @Author ${author}
 * @Date ${date}
 */
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 添加
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    void add(${entity}Request request);

    /**
     * 修改
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    void update(${entity}Request request);

    /**
     * 获取列表
     * @param request 参数
     * @author ${author}
     * @date ${date}
     **/
    List<${entity}> queryList(${entity}QueryRequest request);

    /**
     * 获取分页列表
     * @param pageRequest 参数
     * @author ${author}
     * @date ${date}
     **/
    PageResponse<${entity}> queryPage(PageRequest<${entity}QueryRequest> pageRequest);

}
