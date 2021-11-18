package com.test.dbhappy.gen;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页数据
 *
 */
@Data
@ApiModel(value = "分页对象", description = "")
public class PageDomain implements Serializable
{
    /** 当前记录起始索引 */
    @TableField(exist = false)
    @ApiModelProperty(value = "当前记录起始索引")
    private Integer pageNum;

    /** 每页显示记录数 */
    @TableField(exist = false)
    @ApiModelProperty(value = "每页显示记录数")
    private Integer pageSize;

    /** 排序列 */
    @TableField(exist = false)
    @ApiModelProperty(value = "排序列")
    private String orderByColumn;

    /** 排序的方向 "desc" 或者 "asc". */
    @TableField(exist = false)
    @ApiModelProperty(value = "排序的方向 \"desc\" 或者 \"asc\".")
    private String isAsc;
}
