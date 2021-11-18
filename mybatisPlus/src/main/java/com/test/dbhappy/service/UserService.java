package com.test.dbhappy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.dbhappy.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    int batchInsert(List<User> list);

    int batchUpdate(List<User> list);
}