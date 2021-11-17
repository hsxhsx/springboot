package com.test.kevin.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 胡双喜
 * @since 2021-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "创建者")
    private String author;

    @ApiModelProperty(value = "跟新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "采样厂商")
    private String sampleManufacturer;

    @ApiModelProperty(value = "0q启用，1禁用")
    @TableLogic
    private String deleted;

    @ApiModelProperty(value = "所属角色")
    private String role;

    @ApiModelProperty(value = "账户名称")
    private String account;

    @TableField(exist = false)
    private String pageNumber;

    @TableField(exist = false)
    private String pageSize;

    private String districtCode;

    private String streetSign;

    private String communitySign;

    @TableField(exist = false)
    private String token;


}