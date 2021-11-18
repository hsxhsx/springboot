package com.test.dbhappy.gen;



import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * @program: jhjmember
 * @ClassName generator
 * @description:
 * @author:蒋皓洁
 * @create: 2020-08-18 15:04
 * @Version 1.0
 **/
public class CodeGenerator {


    public static void main(String[] args) {
        //构建一个代码生成器对象
        AutoGenerator mpg = new AutoGenerator();

        //配置执行策略

        //1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String proPath = System.getProperty("user.dir"); //当前项目路径
        gc.setOutputDir(proPath + "/src/main/java"); //设置代码生成路径
        gc.setAuthor("胡双喜");
        gc.setOpen(false); //生成后是否打开文件夹
        gc.setFileOverride(true); //是否覆盖
        gc.setServiceName("%sService"); //去service的 I 前缀
        gc.setIdType(IdType.ASSIGN_UUID); //主键生成策略
        gc.setDateType(DateType.ONLY_DATE); //设置日期类型
        gc.setSwagger2(true); //是否生成Swagger
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/chou_yang?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("hsx122096");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //3、配置生成哪些包，这些包放在哪
        PackageConfig pc=new PackageConfig();
        pc.setModuleName("kevin"); //设置并生成该模块包
        pc.setParent("com.test.dbhappy"); //设置模块存放位置
        pc.setEntity("entity"); //设置并生成存放实体类包
        pc.setMapper("mapper");//设置并生成存放mapper接口的包
        pc.setService("service"); //设置并生成service层所在的包
        pc.setController("controller"); //设置并生成controller层所在的包
        mpg.setPackageInfo(pc);

        //4、策略配置
        StrategyConfig strategy=new StrategyConfig();
        strategy.setInclude("t_user"); //设置要映射的表名！！！！
        strategy.setNaming(NamingStrategy.underline_to_camel); //表名中下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//表中字段如果有下划线，转驼峰命名
        strategy.setEntityLombokModel(true);//自动生成Lombok
        strategy.setRestControllerStyle(true);//开启 RestFul 风格
        strategy.setControllerMappingHyphenStyle(true);
        //设置逻辑删除，对表中的那个字段
        strategy.setTablePrefix(new String[] { "t_" });//
        strategy.setLogicDeleteFieldName("deleted");

        //自动填充 (表中如果有创建时间、修改时间话，可以使用自动填充)
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);
        //乐观锁配置
        strategy.setVersionFieldName("version");
        strategy.setSuperControllerClass("com.test.gen.BaseController");
        strategy.setEntitySerialVersionUID(true);
        mpg.setStrategy(strategy);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController("/templates/templatesMybatis/controller.java.vm");
        tc.setService("/templates/templatesMybatis/service.java.vm");
        tc.setServiceImpl("/templates/templatesMybatis/serviceImpl.java.vm");
        tc.setEntity("/templates/templatesMybatis/entity.java.vm");
        tc.setMapper("/templates/templatesMybatis/mapper.java.vm");
        tc.setXml("/templates/templatesMybatis/mapper.xml.vm");
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。

        mpg.setTemplate(tc);



        mpg.execute();//执行代码生成操作
    }



}

