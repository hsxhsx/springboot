package com.test.dbhappy.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@TableName("t_user")
@Accessors(chain = true)
public class LoginUser {
    private String id;
    @Excel(name = "姓名", orderNum = "0")
    private String username;
    private String password;
    private String role;
    @Excel(name = "创建时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "2")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @Excel(name = "手机号", orderNum = "1")
    private String phoneNumber;

    @ApiModelProperty(value = "创建者")
    private String author;

    @ApiModelProperty(value = "跟新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
