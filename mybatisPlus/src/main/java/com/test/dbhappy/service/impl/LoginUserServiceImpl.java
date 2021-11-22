package com.test.dbhappy.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.dbhappy.entity.LoginUser;
import com.test.dbhappy.mapper.LoginUserMapper;
import com.test.dbhappy.service.LoginUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginUserServiceImpl extends ServiceImpl<LoginUserMapper, LoginUser> implements LoginUserService {

   @Resource
   private LoginUserMapper mapper;

    @Override
    @DS(value = "slave")
    public int batchInsert(List<LoginUser> list) {
        return mapper.insertBatch(list);
    }

    @Override
    public int batchUpdate(List<LoginUser> list) {
        return mapper.updateBatch(list);
    }
}
