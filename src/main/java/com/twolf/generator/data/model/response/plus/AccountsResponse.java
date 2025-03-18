package com.twolf.generator.data.model.response.plus;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 
 * @Author twolf
 * @Date 2025-03-18
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Schema(name = "Accounts", description = "")
public class AccountsResponse {

    /** id */
    @Schema(description = "id")
    private Integer id;

    private String name;

    private String keyword;

    private String password;

    private String token;

    /** 创建时间 */
    @Schema(description = "创建时间")
    private LocalDateTime addTime;

    /** 更新时间 */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /** 逻辑删除 */
    @Schema(description = "逻辑删除")
    private Boolean deleted;

    /** 注册码 */
    @Schema(description = "注册码")
    private String registerCode;

    /** 注册ip地址 */
    @Schema(description = "注册ip地址")
    private String registerIp;

    /** 权限 */
    @Schema(description = "权限")
    private Integer privilege;

    /** 最后一次登录ip地址 */
    @Schema(description = "最后一次登录ip地址")
    private String lastLoginIp;

    /** 设备序列号 */
    @Schema(description = "设备序列号")
    private String mac;

    /** 最后一次登录设备 */
    @Schema(description = "最后一次登录设备")
    private String lastLoginMac;

    /** 逻辑删除 */
    @Schema(description = "逻辑删除")
    private Boolean ban;
}
