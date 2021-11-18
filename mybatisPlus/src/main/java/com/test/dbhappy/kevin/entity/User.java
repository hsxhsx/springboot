package com.test.dbhappy.kevin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 胡双喜
 * @since 2021-11-18
 */
@Data
    @EqualsAndHashCode(callSuper = false)
@TableName("t_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

private static final long serialVersionUID=1L;

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

                private String pageNumber;

                private String pageSize;

                private String districtCode;

                private String streetSign;

                private String communitySign;


}