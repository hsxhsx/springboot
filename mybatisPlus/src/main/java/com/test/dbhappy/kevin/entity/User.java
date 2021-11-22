package com.test.dbhappy.kevin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 胡双喜
 * @since 2021-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "用户名")
    @Excel(name = "姓名", orderNum = "0")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "部门")
    private String department;

    @ApiModelProperty(value = "手机号")
    @Excel(name = "手机号", orderNum = "1")
    private String phoneNumber;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "2")
    private Date createTime;

    @ApiModelProperty(value = "创建者")
    private String author;

    @ApiModelProperty(value = "跟新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "采样厂商")
    private String sampleManufacturer;

    @ApiModelProperty(value = "0q启用，1禁用")
    @TableLogic
    private String deleted;

    @ApiModelProperty(value = "所属角色")
    @Excel(name = "角色", orderNum = "3")
    private String role;

    @ApiModelProperty(value = "账户名称")
    private String account;

    private String pageNumber;

    private String pageSize;

    private String districtCode;

    private String streetSign;

    private String communitySign;


}