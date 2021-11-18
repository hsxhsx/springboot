package com.test.dbhappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.dbhappy.entity.LoginUser;

import java.util.List;

public interface LoginUserService extends IService<LoginUser> {
    int batchInsert(List<LoginUser> list);

    int batchUpdate(List<LoginUser> list);
}