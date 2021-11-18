package com.test.dbhappy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.dbhappy.entity.User;
import com.test.dbhappy.mapper.UserMapper;
import com.test.dbhappy.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

   @Resource
   private UserMapper mapper;

    @Override
    public int batchInsert(List<User> list) {
        return mapper.insertBatch(list);
    }

    @Override
    public int batchUpdate(List<User> list) {
        return mapper.updateBatch(list);
    }
}
