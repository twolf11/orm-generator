package com.twolf.generator.model;

import lombok.Builder;
import lombok.Data;

/**
 * 数据库实体
 * @Author twolf
 * @Date 2025/3/18
 */
@Data
@Builder(toBuilder = true)
public class DatabaseInfo {

    /**
     * 数据库ip
     */
    private String ip;

    /**
     * 数据库port
     */
    private String port;

    /**
     * 数据库名称
     */
    private String database;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 获取配置的jdbcUrl
     * @author twolf
     * @date 2025/3/18 14:07
     **/
    public String getJdbcUrl() {
        return "jdbc:mysql://" + ip + ":" + port + "/" + database + "?useUnicode=true&allowPublicKeyRetrieval=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
    }
}
