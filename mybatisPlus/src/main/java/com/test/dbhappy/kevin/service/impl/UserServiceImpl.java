package com.test.dbhappy.kevin.service.impl;

import com.test.dbhappy.kevin.entity.User;
import com.test.dbhappy.kevin.mapper.UserMapper;
import com.test.dbhappy.kevin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 胡双喜
 * @since 2021-11-18
 */
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
