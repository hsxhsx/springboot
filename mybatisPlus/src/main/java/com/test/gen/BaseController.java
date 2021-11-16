package com.test.gen;



import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * web层通用数据处理
 *
 */
@Slf4j
public class BaseController
{
    /**
     * 设置默认分页页码
     * @param pageDomain
     * @return PageDomain
     */
    protected PageDomain CheckPageDomain(PageDomain pageDomain) {
        if (pageDomain.getPageNum() == null || pageDomain.getPageNum() < 1) {
            pageDomain.setPageNum(1);
        }
        if (pageDomain.getPageSize() == null || pageDomain.getPageSize() < 1) {
            pageDomain.setPageSize(10);
        }
        return pageDomain;
    }

    /**
     *  asc or desc
     * @param oderby
     * @return boolean
     */
    protected boolean orderByCheck(String oderby){
        if (StringUtils.isBlank(oderby) || "asc".equals(oderby)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        PageInfo pi = new PageInfo(list);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setMsg("查询成功!");
        rspData.setRecords(list);
        rspData.setTotal(pi.getTotal());
        rspData.setPages(pi.getPages());
        rspData.setPageNum(pi.getPageNum());
        rspData.setPageSize(pi.getPageSize());
        return rspData;
    }


    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return redirect(url);
    }

    /**
     * 获取配置文件参数
     * */
    public String getConfigValue(String key)
    {
        String val = null;
        String path = File.separator + "application.yml";
        ClassPathResource resource = new ClassPathResource(path);
        try
        {
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            val = (String)props.get(key);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return val;
    }

}
