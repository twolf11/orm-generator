package com.twolf.generator.data.model.domain.plus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Accounts {

    /** id */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String keyword;

    private String password;

    private String token;

    /** 创建时间 */
    private LocalDateTime addTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /** 逻辑删除 */
    private Boolean deleted;

    /** 注册码 */
    private String registerCode;

    /** 注册ip地址 */
    private String registerIp;

    /** 权限 */
    private Integer privilege;

    /** 最后一次登录ip地址 */
    private String lastLoginIp;

    /** 设备序列号 */
    private String mac;

    /** 最后一次登录设备 */
    private String lastLoginMac;

    /** 逻辑删除 */
    private Boolean ban;
}
