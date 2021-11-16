package com.test.gen;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 *
 */

@Data
@ApiModel(value = "分页对象", description = "")
public class TableDataInfo<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    @TableField(exist = false)
    @ApiModelProperty(value = "总记录数")
    private long total;

    /** 总页数 */
    @TableField(exist = false)
    @ApiModelProperty(value = "总页数")
    private int pages;

    /** 当前页 */
    @TableField(exist = false)
    @ApiModelProperty(value = "当前页")
    private int pageNum;

    /** 当前页记录数 */
    @TableField(exist = false)
    @ApiModelProperty(value = "当前页记录数")
    private int pageSize;

    /** 列表数据 */
    @TableField(exist = false)
    @ApiModelProperty(value = "列表数据")
    private List<T> records;

    /** 消息状态码 */
    @TableField(exist = false)
    @ApiModelProperty(value = "消息状态码")
    private int code;

    /** 消息内容 */
    @TableField(exist = false)
    @ApiModelProperty(value = "消息内容")
    private String msg;

    /**
     * 表格数据对象
     */
    public TableDataInfo()
    {
    }

    /**
     * 分页
     *
     * @param list 列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<T> list, int total, int pages)
    {
        this.records = list;
        this.total = total;
        this.pages = pages;
    }
}