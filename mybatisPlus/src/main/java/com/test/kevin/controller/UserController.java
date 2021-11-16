package com.test.kevin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;

import java.util.Objects;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.test.gen.PageDomain;
import com.test.gen.R;
import com.test.gen.TableDataInfo;

import com.test.kevin.service.UserService;
import com.test.kevin.entity.User;

import com.test.gen.BaseController;

/**
 * 前端控制器
 *
 * @author: 胡双喜
 * @date: 2021-11-16
 */
@RestController
@RequestMapping({"user"})
@Api(tags = "管理")
public class UserController extends BaseController {

    @Resource
    private UserService service;

    @GetMapping({"list"})
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "根据实体属性分页查询")
    public R<TableDataInfo<User>> list(User entity, PageDomain pageDomain) {
        PageHelper.startPage(CheckPageDomain(pageDomain));
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.setEntity(entity).orderByAsc("id");
        return R.ok(getDataTable(service.list(qw)));
    }

    @GetMapping({"/getOne"})
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "根据实体id获取详情")
    public R<User> getOne(User entity) {
        QueryWrapper queryWrapper = new QueryWrapper<>(entity);
        return R.ok(service.getOne(queryWrapper));
    }

    @PostMapping({"save"})
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "新增保存实体")
    public R save(@RequestBody @Valid User entity) {
        return R.ok(service.save(entity));
    }

    @PutMapping({"update"})
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "更新实体")
    public R update(@RequestBody User entity) {
        if (Objects.isNull(entity.getId())) {
            return R.fail("参数为空!");
        }
        Object result = service.getById(entity.getId());
        if (Objects.nonNull(result)) {
            return R.ok(service.updateById(entity));
        } else {
            return R.fail("数据不存在!");
        }
    }

    @DeleteMapping({"/remove/{id}"})
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = " 删除实体")
    public R remove(@PathVariable("id") int id) {
        Object entity = service.getById(id);
        if (Objects.nonNull(entity)) {
            return R.ok(service.removeById(id));
        } else {
            return R.fail("数据不存在!");
        }
    }
}
